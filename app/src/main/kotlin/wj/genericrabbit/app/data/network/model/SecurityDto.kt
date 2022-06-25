package wj.genericrabbit.app.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SecurityDto(
	val id: String,
	val profile: ProfileDto?,
	val user: UserDto?,
	val institution: InstitutionDto?,
	val admissedAt: String?
)
