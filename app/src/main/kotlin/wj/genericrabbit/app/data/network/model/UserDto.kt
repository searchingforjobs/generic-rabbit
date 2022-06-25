package wj.genericrabbit.app.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
	val email: String,
	val username: String,
	val banReason: String?,
	val createdAt: String,
	val locale: String
)
