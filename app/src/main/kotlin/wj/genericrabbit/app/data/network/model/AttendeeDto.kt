package wj.genericrabbit.app.data.network.model

import com.squareup.moshi.JsonClass
import wj.genericrabbit.app.domain.model.AttendeeStatus

@JsonClass(generateAdapter = true)
data class AttendeeDto(
	val id: String,
	val driverLicense: DriverLicenseDto?,
	val profile: ProfileDto?,
	val user: UserDto?,
	val passport: PassportDto?,
	val photoUrl: String,
	val contactPhone: String,
	val embedding: String,
	val status: AttendeeStatus
)