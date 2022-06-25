package wj.genericrabbit.app.data.network.model.mapper

import wj.genericrabbit.app.data.mapper.Mapper
import wj.genericrabbit.app.data.network.model.IncidentDto
import wj.genericrabbit.app.domain.model.Incident
import java.time.LocalDateTime
import javax.inject.Inject

interface IncidentMapper : Mapper<IncidentDto, Incident>

class IncidentMapperImpl @Inject constructor() : IncidentMapper {

	override fun invoke(incidentDto: IncidentDto) = Incident(
		incidentDto.id,
		incidentDto.firstName,
		incidentDto.middleName,
		incidentDto.lastName,
		incidentDto.photoUrl,
		incidentDto.description,
		LocalDateTime.parse(incidentDto.createdAt),
		LocalDateTime.parse(incidentDto.updatedAt)
	)
}