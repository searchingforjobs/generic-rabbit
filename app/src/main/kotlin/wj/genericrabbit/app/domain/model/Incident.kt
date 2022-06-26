package wj.genericrabbit.app.domain.model

import java.io.Serializable
import java.time.LocalDateTime

data class Incident(
	val id: String,
	val firstName: String,
	val middleName: String,
	val lastName: String,
	val photoUrl: String,
	val description: String,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime = createdAt
) : Serializable

val Incident.fullName: String
	get() = "$lastName $firstName $middleName"

val Incident.shortName: String
	get() = "$firstName $lastName"