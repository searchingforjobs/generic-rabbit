package wj.genericrabbit.app.ui.incidents.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import wj.genericrabbit.app.domain.model.Incident
import wj.genericrabbit.app.domain.usecase.CreateIncidentUseCase
import javax.inject.Inject

@HiltViewModel
class CreateIncidentViewModel @Inject constructor(
	private val createIncidentUseCase: CreateIncidentUseCase
) : ViewModel(), Flow<CreateIncidentUiState> {

	private val uiState = MutableStateFlow(CreateIncidentUiState())

	override suspend fun collect(collector: FlowCollector<CreateIncidentUiState>) = uiState.collect(collector)

	fun updateCurrentIncidentState(incident: Incident) = uiState.update {
		it.copy(incident = incident)
	}

	fun createIncident() {
		viewModelScope.launch {
			uiState.update {
				it.copy(isLoading = true)
			}
			createIncidentUseCase(uiState.value.incident)
			uiState.update {
				it.copy(
					isLoading = false,
					isUpdated = true
				)
			}
		}
	}
}