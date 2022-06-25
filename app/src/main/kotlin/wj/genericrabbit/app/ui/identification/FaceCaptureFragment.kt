package wj.genericrabbit.app.ui.identification

import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.transform.CoordinateTransform
import androidx.camera.view.transform.ImageProxyTransformFactory
import androidx.core.content.ContextCompat
import androidx.core.graphics.toRect
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.FragmentCaptureFaceBinding
import wj.genericrabbit.app.domain.model.IdentificationData
import wj.genericrabbit.app.ui.identification.util.extension.checkOrRequestCameraPermission
import wj.genericrabbit.app.ui.identification.util.extension.registerForCameraPermissionResult
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private const val TAG = "FaceCamera"
private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

class FaceCaptureFragment : Fragment(R.layout.fragment_capture_face) {

	private val binding by viewBinding(FragmentCaptureFaceBinding::bind)

	private val requestPermissionLauncher = registerForCameraPermissionResult { startCamera() }

	private var imageCapture: ImageCapture? = null
	private lateinit var cameraExecutor: ExecutorService

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		checkOrRequestCameraPermission(requestPermissionLauncher) {
			startCamera()
		}
		binding.buttonCaptureImage.setOnClickListener {
			takePhoto()
		}
		cameraExecutor = Executors.newSingleThreadExecutor()
	}

	@androidx.camera.view.TransformExperimental
	private fun takePhoto() {
		val imageCapture = imageCapture ?: return
		val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis()) + ".jpg"
		imageCapture.takePicture(
			ContextCompat.getMainExecutor(requireContext()),
			object : ImageCapture.OnImageCapturedCallback() {
				override fun onCaptureSuccess(image: ImageProxy) {
					super.onCaptureSuccess(image)
					image.use { imageProxy ->
						val source = binding.previewViewViewFinder.outputTransform
						val target = ImageProxyTransformFactory().getOutputTransform(imageProxy)
						val coordinateTransform = CoordinateTransform(source!!, target)
						val cropRect = with(binding.viewCropBox) {
							RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
						}
						coordinateTransform.mapRect(cropRect)
						val bitmap = imageProxy.crop(cropRect.toRect())
						val file = File(requireContext().filesDir, name)
						saveBitmap(bitmap, file)
						val identificationData = IdentificationData.FacePhoto(file.absolutePath)
						val toResultScreen = FaceCaptureFragmentDirections.faceToResultFragment(identificationData)
						findNavController().navigate(toResultScreen)
					}
				}

				override fun onError(e: ImageCaptureException) {
					Log.e(TAG, "Photo capture failed: ${e.message}", e)
				}
			}
		)
	}

	fun saveBitmap(target: Bitmap, file: File) {
		FileOutputStream(file).use { outputStream ->
			target.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
		}
	}

	private fun ImageProxy.crop(rect: Rect): Bitmap {
		val rotationMatrix = Matrix().apply {
			postRotate(imageInfo.rotationDegrees.toFloat())
		}
		val buffer = planes[0].buffer
		val bytes = ByteArray(buffer.remaining())
		buffer.get(bytes)
		val source = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
		return Bitmap.createBitmap(
			source,
			rect.left,
			rect.top,
			rect.right - rect.left,
			rect.bottom - rect.top,
			rotationMatrix,
			true
		)
	}

	private fun startCamera() {
		val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
		cameraProviderFuture.addListener({
			val cameraProvider = cameraProviderFuture.get()
			val preview = Preview.Builder()
				.build()
				.also {
					it.setSurfaceProvider(binding.previewViewViewFinder.surfaceProvider)
				}
			imageCapture = ImageCapture.Builder()
				.setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
				.build()
			val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
			try {
				cameraProvider.unbindAll()
				cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
			} catch (e: Exception) {
				Log.e(TAG, "Use case binding failed", e)
			}
		}, ContextCompat.getMainExecutor(requireContext()))
	}

	override fun onDestroy() {
		super.onDestroy()
		cameraExecutor.shutdown()
	}
}