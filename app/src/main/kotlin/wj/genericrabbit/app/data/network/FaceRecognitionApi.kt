package wj.genericrabbit.app.data.network

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import wj.genericrabbit.app.data.network.model.FaceRecognitionDto

interface FaceRecognitionApi {

	@Multipart
	@POST("face-recognition")
	suspend fun getAttendeesByPhoto(@Part file: MultipartBody.Part): List<FaceRecognitionDto>
}