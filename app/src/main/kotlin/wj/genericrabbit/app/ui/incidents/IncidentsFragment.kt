package wj.genericrabbit.app.ui.incidents

import android.os.Bundle
import android.view.View
import androidx.core.util.Pair
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.FragmentIncidentsBinding
import wj.genericrabbit.app.ui.util.extension.observeUiState

@AndroidEntryPoint
class IncidentsFragment : Fragment(R.layout.fragment_incidents) {

	private val binding by viewBinding(FragmentIncidentsBinding::bind)
	private val viewModel by viewModels<IncidentsViewModel>()
	private val incidentsListAdapter = IncidentsListAdapter()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.recyclerViewIncidents.adapter = incidentsListAdapter
		observeUiState(viewModel, ::render)
		setupNavigation()
	}

	private fun setupNavigation() {
		binding.buttonIncidentsPickDate.setOnClickListener {
			val picker = MaterialDatePicker.Builder.dateRangePicker()
				.setTitleText("Select dates")
				.setSelection(
					Pair(
						MaterialDatePicker.thisMonthInUtcMilliseconds(),
						MaterialDatePicker.todayInUtcMilliseconds()
					)
				)
				.build()
			picker.addOnPositiveButtonClickListener {
				viewModel.filterByDateRange(it.first, it.second)
			}
			picker.show(requireActivity().supportFragmentManager, "")
		}
		binding.buttonIncidentsClearFilter.setOnClickListener {
			viewModel.clearFilter()
		}
	}

	private fun render(uiState: IncidentsUiState) {
		binding.progressIndicatorIncidentsLoading.isVisible = uiState.isLoading
		binding.recyclerViewIncidents.isVisible = !uiState.isLoading
		incidentsListAdapter.submitList(uiState.incidentsList)
	}
}