package wj.genericrabbit.app.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PassportDto(
	val id: String,
	@Json(name = "firstname") val firstName: String,
	@Json(name = "middlename") val middleName: String?,
	@Json(name = "lastname") val lastName: String,
	val birthdate: String?,
	val gender: String,
	val documentSeries: String,
	val documentNumber: String,
	val placeOfBirth: String,
	val signature: Boolean,
	val stamp: Boolean,
	val issuedBy: String,
	val issuedAt: String,
	val divisionCode: String,
	val scanLink: String
)
