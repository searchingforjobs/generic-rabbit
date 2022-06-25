package wj.genericrabbit.app.domain.model

data class Attendee(
	val id: String,
	val firstName: String,
	val lastName: String,
	val middleName: String,
	val photoUrl: String,
	val status: AttendeeStatus
)

enum class AttendeeStatus {
	PENDING, CONFIRMED
}

enum class Gender {
	MALE, FEMALE, NOT_SPECIFIED
}