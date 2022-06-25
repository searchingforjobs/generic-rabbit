package wj.genericrabbit.app.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IncidentDto(
	val id: String,
	val security: SecurityDto?,
	@Json(name = "firstname") val firstName: String,
	@Json(name = "middlename") val middleName: String,
	@Json(name = "lastname") val lastName: String,
	val photoUrl: String,
	val description: String,
	val createdAt: String,
	val updatedAt: String
)
