package wj.genericrabbit.app.domain.model

import java.time.LocalDateTime

data class Visit(
	val id: String,
	val attendee: Attendee,
	val createdAt: LocalDateTime
)
