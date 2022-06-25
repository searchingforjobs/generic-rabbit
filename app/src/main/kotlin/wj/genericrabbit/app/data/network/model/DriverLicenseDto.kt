package wj.genericrabbit.app.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DriverLicenseDto(
	val id: String,
	@Json(name = "firstname") val firstName: String,
	@Json(name = "middlename") val middleName: String,
	@Json(name = "lastname") val lastName: String,
	val birthdate: String?,
	val issuedBy: String?,
	val cityIssuedIn: String?,
	val regionIssuedIn: String?,
	val signature: Boolean,
	val categories: String,
	val scanLink: String
)
