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
		val attendeeDto = attendeesApi.getAttendeeById(id) ?: return null
		return attendeeMapper(attendeeDto)
	}

	override suspend fun getAttendeesByPhoto(photoPath: String): List<Attendee> {
		val photoFile = File(photoPath)
		val fileRequestBody = photoFile.asRequestBody("application/octet-stream".toMediaType())
		val body = MultipartBody.Builder()
			.addFormDataPart("file", photoFile.name, fileRequestBody)
			.build()
		return faceRecognitionApi.getAttendeesByPhoto(body).map(attendeeMapper)
	}
}