package wj.genericrabbit.app.ui.identification.result

import wj.genericrabbit.app.domain.model.Attendee

data class IdentificationResultUiState(
	val isLoading: Boolean = true,
	val isLoadSuccessful: Boolean = true,
	val attendee: Attendee? = null,
	val similarAttendees: List<Attendee> = emptyList()
)
