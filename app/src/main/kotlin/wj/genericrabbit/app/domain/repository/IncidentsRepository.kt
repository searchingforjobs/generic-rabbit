package wj.genericrabbit.app.domain.repository

import wj.genericrabbit.app.domain.model.Incident

interface IncidentsRepository {
	suspend fun getAllIncidents(): List<Incident>
	suspend fun createIncident(incident: Incident)
}