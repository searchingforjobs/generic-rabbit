package wj.genericrabbit.app.ui.identification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.BottomSheetIdentificationResultBinding
import wj.genericrabbit.app.domain.model.fullName
import wj.genericrabbit.app.ui.util.extension.observeUiState

@AndroidEntryPoint
class IdentificationResultFragment : BottomSheetDialogFragment() {

	private var _binding: BottomSheetIdentificationResultBinding? = null
	private val viewModel by viewModels<IdentificationResultViewModel>()
	private val args by navArgs<IdentificationResultFragmentArgs>()

	private val binding: BottomSheetIdentificationResultBinding
		get() = _binding!!

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_binding = BottomSheetIdentificationResultBinding.inflate(inflater)
		return _binding?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		setupNavigation()
		observeUiState(viewModel, ::render)
		viewModel.loadAttendee(args.identificationData)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	private fun setupNavigation() {
		binding.buttonConfirmationYes.setOnClickListener {

		}
		binding.buttonConfirmationNo.setOnClickListener {

		}
	}

	private fun render(uiState: IdentificationResultUiState) {
		binding.progressIndicatorLoading.isVisible = uiState.isLoading
		binding.identificationResultContent.isVisible = !uiState.isLoading
		if (!uiState.isLoadSuccessful) {
			// TODO: navigate to incident creation
			Toast.makeText(
				requireContext(),
				"Load unsuccessful",
				Toast.LENGTH_SHORT
			).show()
		}
		uiState.attendee?.let { attendee ->
			binding.imageViewAttendeePhoto.load(attendee.photoUrl) {
				crossfade(true)
			}
			binding.textViewAttendeeName.text = attendee.fullName
			if (attendee.fullName.isBlank()) {
				binding.textViewAttendeeName.setText(R.string.unknown)
			}
		}
	}
}