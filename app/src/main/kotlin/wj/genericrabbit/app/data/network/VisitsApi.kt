package wj.genericrabbit.app.data.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import wj.genericrabbit.app.data.network.model.CreateVisitDto
import wj.genericrabbit.app.data.network.model.VisitDto

interface VisitsApi {

	@GET("visits")
	suspend fun getAllVisits(): List<VisitDto>

	@POST("visits")
	suspend fun createVisit(@Body createVisitDto: CreateVisitDto)
}