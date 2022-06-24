package wj.genericrabbit.app.ui.profile.edit

import wj.genericrabbit.app.domain.model.User

data class EditProfileUiState(
	val user: User? = null,
	val isLoading: Boolean = true,
	val isUpdated: Boolean = false
)
