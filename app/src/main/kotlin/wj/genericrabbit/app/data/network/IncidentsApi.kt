package wj.genericrabbit.app.data.network

import okhttp3.MultipartBody
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
		@Part("file") file: MultipartBody.Part,
		@Part("securityId") securityId: MultipartBody.Part,
		@Part("firstname") firstName: MultipartBody.Part,
		@Part("middlename") middleName: MultipartBody.Part,
		@Part("lastname") lastName: MultipartBody.Part,
		@Part("description") description: MultipartBody.Part
	)
}