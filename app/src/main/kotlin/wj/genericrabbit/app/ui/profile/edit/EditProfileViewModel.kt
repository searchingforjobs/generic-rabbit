package wj.genericrabbit.app.ui.profile.edit

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import wj.genericrabbit.app.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
) : ViewModel(), Flow<EditProfileUiState> {

	private val uiState = MutableStateFlow(EditProfileUiState())

	override suspend fun collect(collector: FlowCollector<EditProfileUiState>) = uiState.collect(collector)

	fun updateCurrentUserState(user: User) = uiState.update {
		it.copy(user = user)
	}
}