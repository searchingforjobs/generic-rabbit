package wj.genericrabbit.app.domain.usecase

import wj.genericrabbit.app.domain.model.Attendee
import wj.genericrabbit.app.domain.repository.AttendeesRepository
import javax.inject.Inject

interface GetAttendeesByPhotoUseCase {
	suspend operator fun invoke(photoPath: String): List<Attendee>
}

class GetAttendeesByPhotoUseCaseImpl @Inject constructor(private val attendeesRepository: AttendeesRepository) :
	GetAttendeesByPhotoUseCase {
	override suspend fun invoke(photoPath: String) = attendeesRepository.getAttendeesByPhoto(photoPath)
}