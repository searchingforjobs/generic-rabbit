package wj.genericrabbit.app.data.repository

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import wj.genericrabbit.app.data.network.AttendeesApi
import wj.genericrabbit.app.data.network.FaceRecognitionApi
import wj.genericrabbit.app.data.network.model.mapper.AttendeeMapper
import wj.genericrabbit.app.domain.model.Attendee
import wj.genericrabbit.app.domain.repository.AttendeesRepository
import java.io.File
import javax.inject.Inject

class AttendeesRepositoryImpl @Inject constructor(
	private val attendeesApi: AttendeesApi,
	private val faceRecognitionApi: FaceRecognitionApi,
	private val attendeeMapper: AttendeeMapper
) : AttendeesRepository {

	override suspend fun getAttendeeById(id: String): Attendee? {
		val attendeeDtoResponse = attendeesApi.getAttendeeById(id)
		val body = attendeeDtoResponse.body() ?: return null
		return if (attendeeDtoResponse.isSuccessful) attendeeMapper(body) else null
	}

	override suspend fun getAttendeesByPhoto(photoPath: String): List<Attendee> {
		val photoFile = File(photoPath)
		val fileRequestBody = photoFile.asRequestBody("application/octet-stream".toMediaType())
		val fileBody = MultipartBody.Part.createFormData("file", photoFile.name, fileRequestBody)
		return faceRecognitionApi.getAttendeesByPhoto(fileBody).map(attendeeMapper)
	}
}