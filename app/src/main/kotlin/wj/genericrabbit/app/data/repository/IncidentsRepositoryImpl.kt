package wj.genericrabbit.app.data.repository

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import wj.genericrabbit.app.data.network.IncidentsApi
import wj.genericrabbit.app.data.network.model.mapper.IncidentMapper
import wj.genericrabbit.app.domain.model.Incident
import wj.genericrabbit.app.domain.repository.IncidentsRepository
import java.io.File
import javax.inject.Inject

class IncidentsRepositoryImpl @Inject constructor(
	private val incidentsApi: IncidentsApi,
	private val incidentMapper: IncidentMapper
) : IncidentsRepository {

	override suspend fun getAllIncidents() = incidentsApi.getAllIncidents().map(incidentMapper)

	override suspend fun createIncident(incident: Incident) {
		val photoFile = File(incident.photoUrl)
		val fileRequestBody = photoFile.asRequestBody("application/octet-stream".toMediaType())
		val fileBody = MultipartBody.Part.createFormData("file", photoFile.name, fileRequestBody)
		val textMediaType = "text/plain".toMediaType()
		incidentsApi.createIncident(
			fileBody,
			// TODO: securityId
			"securityId".toRequestBody(textMediaType),
			incident.firstName.toRequestBody(textMediaType),
			incident.middleName.toRequestBody(textMediaType),
			incident.lastName.toRequestBody(textMediaType),
			incident.description.toRequestBody(textMediaType)
		)
	}
}