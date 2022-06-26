package wj.genericrabbit.app.domain.model

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

data class Incident(
	val id: String = UUID.randomUUID().toString(),
	val firstName: String = "",
	val middleName: String = "",
	val lastName: String = "",
	val photoUrl: String? = null,
	val description: String = "",
	val createdAt: LocalDateTime = LocalDateTime.now(),
	val updatedAt: LocalDateTime = createdAt
) : Serializable

val Incident.fullName: String
	get() = "$lastName $firstName $middleName"

val Incident.shortName: String
	get() = "$firstName $lastName"