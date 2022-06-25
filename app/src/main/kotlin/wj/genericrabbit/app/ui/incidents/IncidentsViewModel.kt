package wj.genericrabbit.app.ui.incidents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import wj.genericrabbit.app.domain.model.Incident
import wj.genericrabbit.app.domain.usecase.GetAllIncidentsUseCase
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

@HiltViewModel
class IncidentsViewModel @Inject constructor(
	private val getAllIncidentsUseCase: GetAllIncidentsUseCase
) : ViewModel(), Flow<IncidentsUiState> {

	private val uiState = MutableStateFlow(IncidentsUiState())
	private var incidentsList = emptyList<Incident>()

	init {
		viewModelScope.launch {
			uiState.update {
				it.copy(isLoading = true)
			}
			val incidentsList = getAllIncidentsUseCase()
			this@IncidentsViewModel.incidentsList = incidentsList
			uiState.update {
				it.copy(
					isLoading = false,
					incidentsList = incidentsList
				)
			}
		}
	}

	override suspend fun collect(collector: FlowCollector<IncidentsUiState>) = uiState.collect(collector)

	fun filterByDateRange(fromEpochMillis: Long, toEpochMillis: Long) {
		val from = LocalDateTime.ofEpochSecond(fromEpochMillis / 1000, 0, ZoneOffset.UTC)
		val to = LocalDateTime.ofEpochSecond(toEpochMillis / 1000, 0, ZoneOffset.UTC)
		val filteredList = incidentsList.filter {
			it.createdAt.isDateTimeWithinRange(from, to)
		}
		uiState.update {
			it.copy(
				incidentsList = filteredList
			)
		}
	}

	fun clearFilter() {
		uiState.update {
			it.copy(
				incidentsList = incidentsList
			)
		}
	}

	private fun LocalDateTime.isDateTimeWithinRange(from: LocalDateTime, to: LocalDateTime) =
		(isAfter(from) || equals(from)) && (isBefore(to) || equals(to))
}