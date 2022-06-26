package wj.genericrabbit.app.ui.incidents.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import wj.genericrabbit.app.SecurityAppNavigationDirections
import wj.genericrabbit.app.databinding.BottomSheetCreateIncidentBinding
import wj.genericrabbit.app.domain.model.Incident
import wj.genericrabbit.app.ui.util.extension.observeUiState
import wj.genericrabbit.app.ui.util.extension.onBackPressed

@AndroidEntryPoint
class CreateIncidentFragment : BottomSheetDialogFragment() {

	private var _binding: BottomSheetCreateIncidentBinding? = null

	private val binding: BottomSheetCreateIncidentBinding
		get() = _binding!!

	private val viewModel by viewModels<CreateIncidentViewModel>()
	private val args by navArgs<CreateIncidentFragmentArgs>()
	private var currentIncident = Incident()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_binding = BottomSheetCreateIncidentBinding.inflate(inflater)
		return _binding?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		onBackPressed {
			navigateToHomeScreen()
		}
		viewModel.updateCurrentIncidentState(currentIncident.copy(photoUrl = args.photoUrl))
		binding.buttonCreateIncident.setOnClickListener {
			viewModel.createIncident()
		}
		addEditTextListeners()
		observeUiState(viewModel, ::render)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	private fun render(uiState: CreateIncidentUiState) {
		val incident = uiState.incident
		currentIncident = incident
		binding.progressBarCreateIncidentLoading.isVisible = uiState.isLoading
		binding.groupCreateIncidentFields.isVisible = !uiState.isLoading
		binding.textFieldCreateIncidentFirstName.editText?.setString(incident.firstName)
		binding.textFieldCreateIncidentLastName.editText?.setString(incident.lastName)
		binding.textFieldCreateIncidentMiddleName.editText?.setString(incident.middleName)
		binding.textFieldCreateIncidentDescription.editText?.setString(incident.description)
		if (uiState.isUpdated) {
			navigateToHomeScreen()
		}
	}

	private fun navigateToHomeScreen() {
		val toHomeScreen = SecurityAppNavigationDirections.actionGlobalHome()
		findNavController().navigate(toHomeScreen)
	}

	private fun addEditTextListeners() {
		binding.textFieldCreateIncidentFirstName.editText?.updateIncident { text, incident ->
			incident.copy(firstName = text.toString())
		}
		binding.textFieldCreateIncidentLastName.editText?.updateIncident { text, incident ->
			incident.copy(lastName = text.toString())
		}
		binding.textFieldCreateIncidentMiddleName.editText?.updateIncident { text, incident ->
			incident.copy(middleName = text.toString())
		}
		binding.textFieldCreateIncidentDescription.editText?.updateIncident { text, incident ->
			incident.copy(description = text.toString())
		}
	}

	private fun EditText.setString(string: String) {
		setText(string)
		text?.let { text ->
			setSelection(text.length)
		}
	}

	private inline fun EditText.updateIncident(crossinline transform: (CharSequence?, Incident) -> Incident) {
		addTextChangedListener(
			onTextChanged = { text, _, _, _ ->
				viewModel.updateCurrentIncidentState(transform(text, currentIncident))
			}
		)
	}
}