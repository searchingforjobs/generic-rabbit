package wj.genericrabbit.app.domain.usecase

import wj.genericrabbit.app.domain.model.Incident
import wj.genericrabbit.app.domain.repository.IncidentsRepository
import javax.inject.Inject

interface GetAllIncidentsUseCase {
	suspend operator fun invoke(): List<Incident>
}

class GetAllIncidentsUseCaseImpl @Inject constructor(private val incidentsRepository: IncidentsRepository) :
	GetAllIncidentsUseCase {

	override suspend fun invoke() = incidentsRepository.getAllIncidents()
}