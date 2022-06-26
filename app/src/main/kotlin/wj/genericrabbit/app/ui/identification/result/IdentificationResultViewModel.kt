package wj.genericrabbit.app.ui.identification.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import wj.genericrabbit.app.domain.model.IdentificationData
import wj.genericrabbit.app.domain.usecase.GetAttendeeByIdUseCase
import wj.genericrabbit.app.domain.usecase.GetAttendeesByPhotoUseCase
import javax.inject.Inject

@HiltViewModel
class IdentificationResultViewModel @Inject constructor(
	private val getAttendeeByIdUseCase: GetAttendeeByIdUseCase,
	private val getAttendeesByPhotoUseCase: GetAttendeesByPhotoUseCase
) : ViewModel(), Flow<IdentificationResultUiState> {

	private val uiState = MutableStateFlow(IdentificationResultUiState())

	override suspend fun collect(collector: FlowCollector<IdentificationResultUiState>) = uiState.collect(collector)

	fun loadAttendee(identificationData: IdentificationData) {
		viewModelScope.launch {
			uiState.update {
				it.copy(isLoading = true)
			}
			val attendee = when (identificationData) {
				is IdentificationData.FacePhoto -> getAttendeesByPhotoUseCase(identificationData.photoPath).firstOrNull()
				is IdentificationData.QR -> getAttendeeByIdUseCase(identificationData.attendeeId)
			}
			uiState.update {
				it.copy(
					isLoading = false,
					isLoadSuccessful = attendee != null,
					attendee = attendee
				)
			}
		}
	}
}