package wj.genericrabbit.app.ui.identification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.zxing.BarcodeFormat
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.FragmentCaptureQrBinding
import wj.genericrabbit.app.domain.model.IdentificationData

class QrCaptureFragment : Fragment(R.layout.fragment_capture_qr) {

	private val binding by viewBinding(FragmentCaptureQrBinding::bind)
	private var codeScanner: CodeScanner? = null

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		val scannerView = binding.scannerView
		val activity = requireActivity()
		val codeScanner = CodeScanner(activity, scannerView).apply {
			camera = CodeScanner.CAMERA_BACK
			formats = listOf(BarcodeFormat.QR_CODE)
			autoFocusMode = AutoFocusMode.CONTINUOUS
			scanMode = ScanMode.SINGLE
			isAutoFocusEnabled = true
		}
		codeScanner.decodeCallback = DecodeCallback {
			activity.runOnUiThread {
				val identificationData = IdentificationData.QR(it.text)
				val toResultScreen = QrCaptureFragmentDirections.qrToResultFragment(identificationData)
				findNavController().navigate(toResultScreen)
			}
		}
		scannerView.setOnClickListener {
			codeScanner.startPreview()
		}
		this.codeScanner = codeScanner
	}

	override fun onResume() {
		super.onResume()
		codeScanner?.startPreview()
	}

	override fun onPause() {
		codeScanner?.releaseResources()
		super.onPause()
	}
}