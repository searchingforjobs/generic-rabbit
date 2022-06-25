package wj.genericrabbit.app.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

	private val binding by viewBinding(FragmentHomeBinding::bind)
	private val viewModel by viewModels<HomeViewModel>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		setupNavigation()
	}

	private fun setupNavigation() {
		binding.buttonQr.setOnClickListener {
			val toQrCaptureScreen = HomeFragmentDirections.homeToQrCaptureFragment()
			findNavController().navigate(toQrCaptureScreen)
		}
		binding.buttonFace.setOnClickListener {
			val toFaceCaptureScreen = HomeFragmentDirections.homeToFaceCaptureFragment()
			findNavController().navigate(toFaceCaptureScreen)
		}
	}
}