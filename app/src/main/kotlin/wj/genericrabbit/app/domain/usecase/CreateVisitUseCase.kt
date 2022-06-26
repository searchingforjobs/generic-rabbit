package wj.genericrabbit.app.domain.usecase

import wj.genericrabbit.app.domain.model.Attendee
import wj.genericrabbit.app.domain.model.Visit
import wj.genericrabbit.app.domain.repository.VisitsRepository
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

interface CreateVisitUseCase {
	suspend operator fun invoke(attendee: Attendee)
}

class CreateVisitUseCaseImpl @Inject constructor(private val visitsRepository: VisitsRepository) : CreateVisitUseCase {

	override suspend operator fun invoke(attendee: Attendee) {
		val visit = Visit(UUID.randomUUID().toString(), attendee, LocalDateTime.now())
		visitsRepository.createVisit(visit)
	}
}