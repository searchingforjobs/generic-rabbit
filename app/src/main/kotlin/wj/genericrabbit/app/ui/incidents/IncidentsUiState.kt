package wj.genericrabbit.app.ui.incidents

import wj.genericrabbit.app.domain.model.Incident

data class IncidentsUiState(
	val isLoading: Boolean = true,
	val incidentsList: List<Incident> = emptyList()
)
