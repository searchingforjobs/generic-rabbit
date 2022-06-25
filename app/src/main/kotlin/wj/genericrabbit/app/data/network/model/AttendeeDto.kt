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
	val embedding: Array<Double>,
	val status: AttendeeStatus
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as AttendeeDto

		if (id != other.id) return false
		if (driverLicense != other.driverLicense) return false
		if (profile != other.profile) return false
		if (user != other.user) return false
		if (passport != other.passport) return false
		if (photoUrl != other.photoUrl) return false
		if (contactPhone != other.contactPhone) return false
		if (!embedding.contentEquals(other.embedding)) return false
		if (status != other.status) return false

		return true
	}

	override fun hashCode(): Int {
		var result = id.hashCode()
		result = 31 * result + (driverLicense?.hashCode() ?: 0)
		result = 31 * result + (profile?.hashCode() ?: 0)
		result = 31 * result + (user?.hashCode() ?: 0)
		result = 31 * result + (passport?.hashCode() ?: 0)
		result = 31 * result + photoUrl.hashCode()
		result = 31 * result + contactPhone.hashCode()
		result = 31 * result + embedding.contentHashCode()
		result = 31 * result + status.hashCode()
		return result
	}
}