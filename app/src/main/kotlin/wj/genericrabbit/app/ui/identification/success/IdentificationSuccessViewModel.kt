package wj.genericrabbit.app.ui.identification.success

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import wj.genericrabbit.app.domain.model.Attendee
import wj.genericrabbit.app.domain.usecase.CreateVisitUseCase
import javax.inject.Inject

@HiltViewModel
class IdentificationSuccessViewModel @Inject constructor(
	private val createVisitUseCase: CreateVisitUseCase
) : ViewModel() {

	fun createVisit(attendee: Attendee) {
		viewModelScope.launch {
			createVisitUseCase(attendee)
		}
	}
}