package wj.genericrabbit.app.ui.identification.success

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import wj.genericrabbit.app.R
import wj.genericrabbit.app.ui.util.extension.onBackPressed

@AndroidEntryPoint
class IdentificationSuccessFragment : Fragment(R.layout.fragment_identification_success) {

	private val viewModel by viewModels<IdentificationSuccessViewModel>()
	private val args by navArgs<IdentificationSuccessFragmentArgs>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		onBackPressed {
			val toHomeScreen = IdentificationSuccessFragmentDirections.successToHomeFragment()
			findNavController().navigate(toHomeScreen)
		}
		viewModel.createVisit(args.attendee)
	}
}