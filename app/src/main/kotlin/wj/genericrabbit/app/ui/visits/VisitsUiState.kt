package wj.genericrabbit.app.ui.visits

import wj.genericrabbit.app.domain.model.Visit

data class VisitsUiState(
	val isLoading: Boolean = true,
	val visitsList: List<Visit> = emptyList()
)
