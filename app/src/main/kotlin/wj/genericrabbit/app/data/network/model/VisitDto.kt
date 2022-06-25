package wj.genericrabbit.app.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VisitDto(
	val id: String,
	val security: SecurityDto,
	val attendee: AttendeeDto,
	val createdAt: String
)