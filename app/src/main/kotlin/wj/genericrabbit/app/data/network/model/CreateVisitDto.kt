package wj.genericrabbit.app.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateVisitDto(
	val securityId: String,
	val attendeeId: String
)
