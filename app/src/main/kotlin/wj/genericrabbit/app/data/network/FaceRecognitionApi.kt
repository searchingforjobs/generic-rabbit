package wj.genericrabbit.app.data.network

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import wj.genericrabbit.app.data.network.model.AttendeeDto

interface FaceRecognitionApi {

	@GET("face-recognition")
	suspend fun getAttendeesByPhoto(@Body body: RequestBody): List<AttendeeDto>
}