package wj.genericrabbit.app.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileDto(
	val id: String,
	@Json(name = "firstname") val firstName: String,
	@Json(name = "middlename") val middleName: String?,
	@Json(name = "lastname") val lastName: String,
	val birthdate: String?,
	val gender: String,
	val createdAt: String,
	val updatedAt: String,
)
