package wj.genericrabbit.app.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import wj.genericrabbit.app.data.network.model.AttendeeDto

interface AttendeesApi {

	@GET("attendees/{id}")
	suspend fun getAttendeeById(@Path("id") id: String): Response<AttendeeDto?>
}