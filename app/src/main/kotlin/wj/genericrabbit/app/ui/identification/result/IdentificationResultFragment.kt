package wj.genericrabbit.app.ui.identification.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.BottomSheetIdentificationResultBinding
import wj.genericrabbit.app.domain.model.Attendee
import wj.genericrabbit.app.domain.model.fullName
import wj.genericrabbit.app.ui.util.extension.observeUiState

@AndroidEntryPoint
class IdentificationResultFragment : BottomSheetDialogFragment() {

	private var _binding: BottomSheetIdentificationResultBinding? = null
	private val viewModel by viewModels<IdentificationResultViewModel>()
	private val args by navArgs<IdentificationResultFragmentArgs>()
	private var currentAttendee: Attendee? = null

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
		binding.buttonIdentificationConfirmationYes.setOnClickListener {
			currentAttendee?.let {
				val toSuccessScreen = IdentificationResultFragmentDirections.resultToSuccessFragment(it)
				findNavController().navigate(toSuccessScreen)
			}
		}
		binding.buttonIdentificationConfirmationNo.setOnClickListener {
			openCreateIncidentDialog()
		}
	}

	private fun render(uiState: IdentificationResultUiState) {
		currentAttendee = uiState.attendee
		binding.progressIndicatorIdentificationLoading.isVisible = uiState.isLoading
		binding.identificationResultContent.isVisible = !uiState.isLoading
		if (!uiState.isLoadSuccessful) {
			openCreateIncidentDialog()
		}
		uiState.attendee?.let { attendee ->
			binding.imageViewIdentificationAttendeePhoto.load(attendee.photoUrl) {
				crossfade(true)
			}
			binding.textViewIdentificationAttendeeName.text = attendee.fullName
			if (attendee.fullName.isBlank()) {
				binding.textViewIdentificationAttendeeName.setText(R.string.unknown)
			}
		}
	}

	private fun openCreateIncidentDialog() {
		val photoPath = viewModel.getPhotoPath(args.identificationData)
		val toCreateIncidentDialog = IdentificationResultFragmentDirections.resultToCreateIncidentFragment(photoPath)
		findNavController().navigate(toCreateIncidentDialog)
	}
}