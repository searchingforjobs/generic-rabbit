package wj.genericrabbit.app.domain.repository

import wj.genericrabbit.app.domain.model.Visit

interface VisitsRepository {
	suspend fun getAllVisits(): List<Visit>
	suspend fun createVisit(visit: Visit)
}