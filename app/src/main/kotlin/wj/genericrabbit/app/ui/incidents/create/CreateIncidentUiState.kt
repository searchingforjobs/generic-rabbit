package wj.genericrabbit.app.ui.incidents.create

import wj.genericrabbit.app.domain.model.Incident

data class CreateIncidentUiState(
	val incident: Incident = Incident(),
	val isLoading: Boolean = false,
	val isUpdated: Boolean = false
)
