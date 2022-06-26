package wj.genericrabbit.app.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FaceRecognitionDto(
	val attendee: AttendeeDto,
	val distance: Double,
	val interpretation: Boolean
)
