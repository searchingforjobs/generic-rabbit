package wj.genericrabbit.app.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : ViewModel(), Flow<LoginUiState> {

	private val uiState = MutableStateFlow(LoginUiState())

	override suspend fun collect(collector: FlowCollector<LoginUiState>) = uiState.collect(collector)
}