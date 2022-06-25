package wj.genericrabbit.app.data.repository

import wj.genericrabbit.app.data.network.VisitsApi
import wj.genericrabbit.app.data.network.model.CreateVisitDto
import wj.genericrabbit.app.data.network.model.mapper.VisitMapper
import wj.genericrabbit.app.domain.model.Visit
import wj.genericrabbit.app.domain.repository.VisitsRepository
import javax.inject.Inject

class VisitsRepositoryImpl @Inject constructor(
	private val visitsApi: VisitsApi,
	private val visitMapper: VisitMapper
) : VisitsRepository {

	override suspend fun getAllVisits() = visitsApi.getAllVisits().map(visitMapper)

	override suspend fun createVisit(visit: Visit) {
		val createVisitDto = CreateVisitDto(
			"2e117740-f6cb-4b70-ba09-44b11c2184c3",
			visit.attendee.id
		)
		visitsApi.createVisit(createVisitDto)
	}
}