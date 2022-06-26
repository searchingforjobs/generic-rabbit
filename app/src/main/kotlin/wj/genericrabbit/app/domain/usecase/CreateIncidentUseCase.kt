package wj.genericrabbit.app.domain.usecase

import wj.genericrabbit.app.domain.model.Incident
import wj.genericrabbit.app.domain.repository.IncidentsRepository
import javax.inject.Inject

interface CreateIncidentUseCase {
	suspend operator fun invoke(incident: Incident)
}

class CreateIncidentUseCaseImpl @Inject constructor(private val incidentsRepository: IncidentsRepository) :
	CreateIncidentUseCase {

	override suspend operator fun invoke(incident: Incident) = incidentsRepository.createIncident(incident)
}