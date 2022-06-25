package wj.genericrabbit.app.data.network.model.mapper

import wj.genericrabbit.app.data.mapper.Mapper
import wj.genericrabbit.app.data.network.model.AttendeeDto
import wj.genericrabbit.app.domain.model.Attendee
import javax.inject.Inject

interface AttendeeMapper : Mapper<AttendeeDto, Attendee>

class AttendeeMapperImpl @Inject constructor() : AttendeeMapper {

	override fun invoke(attendeeDto: AttendeeDto) = Attendee(
		attendeeDto.id,
		attendeeDto.profile?.firstName ?: "",
		attendeeDto.profile?.lastName ?: "",
		attendeeDto.profile?.middleName ?: "",
		attendeeDto.photoUrl,
		attendeeDto.status
	)
}