package wj.genericrabbit.app.domain.repository

import wj.genericrabbit.app.domain.model.Attendee

interface AttendeesRepository {
	suspend fun getAttendeeById(id: String): Attendee?
	suspend fun getAttendeesByPhoto(photoPath: String): List<Attendee>
}