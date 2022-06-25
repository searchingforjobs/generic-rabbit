package wj.genericrabbit.app.data.network.model.mapper

import wj.genericrabbit.app.data.mapper.Mapper
import wj.genericrabbit.app.data.network.model.VisitDto
import wj.genericrabbit.app.domain.model.Visit
import java.time.LocalDateTime
import javax.inject.Inject

interface VisitMapper : Mapper<VisitDto, Visit>

class VisitMapperImpl @Inject constructor(private val attendeeMapper: AttendeeMapper) : VisitMapper {

	override fun invoke(visitDto: VisitDto) = Visit(
		visitDto.id,
		attendeeMapper(visitDto.attendee),
		LocalDateTime.parse(visitDto.createdAt)
	)
}