package wj.genericrabbit.app.data.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import wj.genericrabbit.app.data.network.model.IncidentDto

interface IncidentsApi {

	@GET("incidents")
	suspend fun getAllIncidents(): List<IncidentDto>

	@Multipart
	@POST("incidents")
	suspend fun createIncident(
		@Part file: MultipartBody.Part,
		@Part("securityId") securityId: RequestBody,
		@Part("firstname") firstName: RequestBody,
		@Part("middlename") middleName: RequestBody,
		@Part("lastname") lastName: RequestBody,
		@Part("description") description: RequestBody
	)
}