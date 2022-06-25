package wj.genericrabbit.app.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class InstitutionDto(
	val id: String,
	val type: InstitutionType?,
	val name: String,
	val region: String,
	val postalCode: String?,
	val street: String,
	val locality: String,
	val building: String,
	val contactPhone: String?,
	val contactEmail: String?
)

enum class InstitutionType {
	KINDERGARTEN, SCHOOL
}