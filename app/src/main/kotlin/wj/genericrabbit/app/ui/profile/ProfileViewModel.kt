package wj.genericrabbit.app.ui.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
) : ViewModel(), Flow<ProfileUiState> {

	private val uiState = MutableStateFlow(ProfileUiState())

	override suspend fun collect(collector: FlowCollector<ProfileUiState>) = uiState.collect(collector)
}