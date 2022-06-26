package wj.genericrabbit.app.ui.visits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import wj.genericrabbit.app.domain.model.Visit
import wj.genericrabbit.app.domain.usecase.GetAllVisitsUseCase
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

@HiltViewModel
class VisitsViewModel @Inject constructor(
	private val getAllVisitsUseCase: GetAllVisitsUseCase
) : ViewModel(), Flow<VisitsUiState> {

	private val uiState = MutableStateFlow(VisitsUiState())
	private var visitsList = emptyList<Visit>()

	init {
		update()
	}

	override suspend fun collect(collector: FlowCollector<VisitsUiState>) = uiState.collect(collector)

	fun update() {
		viewModelScope.launch {
			uiState.update {
				it.copy(isLoading = true)
			}
			val visitsList = getAllVisitsUseCase()
			this@VisitsViewModel.visitsList = visitsList
			uiState.update {
				it.copy(
					isLoading = false,
					visitsList = visitsList
				)
			}
		}
	}

	fun filterByDateRange(fromEpochMillis: Long, toEpochMillis: Long) {
		val from = LocalDateTime.ofEpochSecond(fromEpochMillis / 1000, 0, ZoneOffset.UTC)
		val to = LocalDateTime.ofEpochSecond(toEpochMillis / 1000, 0, ZoneOffset.UTC)
		val filteredList = visitsList.filter {
			it.createdAt.isDateTimeWithinRange(from, to)
		}
		uiState.update {
			it.copy(
				visitsList = filteredList
			)
		}
	}

	fun clearFilter() {
		uiState.update {
			it.copy(
				visitsList = visitsList
			)
		}
	}

	private fun LocalDateTime.isDateTimeWithinRange(from: LocalDateTime, to: LocalDateTime) =
		(isAfter(from) || equals(from)) && (isBefore(to) || equals(to))
}