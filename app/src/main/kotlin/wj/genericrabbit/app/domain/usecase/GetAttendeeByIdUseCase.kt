package wj.genericrabbit.app.domain.usecase

import wj.genericrabbit.app.domain.model.Attendee
import wj.genericrabbit.app.domain.repository.AttendeesRepository
import javax.inject.Inject

interface GetAttendeeByIdUseCase {
	suspend operator fun invoke(id: String): Attendee?
}

class GetAttendeeByIdUseCaseImpl @Inject constructor(private val attendeesRepository: AttendeesRepository) :
	GetAttendeeByIdUseCase {

	override suspend fun invoke(id: String) = attendeesRepository.getAttendeeById(id)
}