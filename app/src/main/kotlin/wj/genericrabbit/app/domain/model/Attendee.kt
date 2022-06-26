package wj.genericrabbit.app.domain.model

import java.io.Serializable

data class Attendee(
	val id: String,
	val firstName: String,
	val lastName: String,
	val middleName: String,
	val photoUrl: String,
	val status: AttendeeStatus
) : Serializable

val Attendee.fullName: String
	get() = "$lastName $firstName $middleName"

val Attendee.shortName: String
	get() = "$firstName $lastName"

enum class AttendeeStatus {
	PENDING, CONFIRMED
}

enum class Gender {
	MALE, FEMALE, NOT_SPECIFIED
}