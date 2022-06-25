package wj.genericrabbit.app.ui.identification.util.extension

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import wj.genericrabbit.app.R

const val CAMERA_PERMISSION = Manifest.permission.CAMERA

inline fun Fragment.registerForCameraPermissionResult(crossinline onGranted: () -> Unit) =
	registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
		if (granted) {
			onGranted()
		} else {
			Toast.makeText(
				requireContext(),
				R.string.camera_permission_not_granted,
				Toast.LENGTH_SHORT
			).show()
			findNavController().popBackStack()
		}
	}

inline fun Fragment.checkOrRequestCameraPermission(launcher: ActivityResultLauncher<String>, onGranted: () -> Unit) {
	if (isCameraPermissionGranted()) {
		onGranted()
	} else {
		launcher.launch(CAMERA_PERMISSION)
	}
}

fun Fragment.isCameraPermissionGranted() =
	ContextCompat.checkSelfPermission(requireContext(), CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED