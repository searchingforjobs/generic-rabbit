package wj.genericrabbit.app.ui.visits

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
import wj.genericrabbit.app.databinding.FragmentListBinding
import wj.genericrabbit.app.ui.util.extension.observeUiState

@AndroidEntryPoint
class VisitsFragment : Fragment(R.layout.fragment_list) {

	private val binding by viewBinding(FragmentListBinding::bind)
	private val viewModel by viewModels<VisitsViewModel>()
	private val visitsListAdapter = VisitsListAdapter()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.recyclerViewList.adapter = visitsListAdapter
		observeUiState(viewModel, ::render)
		setupNavigation()
		setupRefresh()
	}

	private fun setupNavigation() {
		binding.buttonListPickDate.setOnClickListener {
			val picker = MaterialDatePicker.Builder.dateRangePicker()
				.setTitleText(R.string.title_select_dates)
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
		binding.buttonListClearFilter.setOnClickListener {
			viewModel.clearFilter()
		}
	}

	private fun setupRefresh() {
		binding.swipeRefreshLayoutList.setOnRefreshListener {
			viewModel.update()
		}
	}

	private fun render(uiState: VisitsUiState) {
		binding.recyclerViewList.isVisible = !uiState.isLoading
		binding.swipeRefreshLayoutList.isRefreshing = uiState.isLoading
		visitsListAdapter.submitList(uiState.visitsList)
	}
}