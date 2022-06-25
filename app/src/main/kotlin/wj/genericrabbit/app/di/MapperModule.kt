package wj.genericrabbit.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wj.genericrabbit.app.data.network.model.mapper.*

@InstallIn(SingletonComponent::class)
@Module
interface MapperModule {

	@Binds
	fun bindAttendeeMapper(attendeeMapperImpl: AttendeeMapperImpl): AttendeeMapper

	@Binds
	fun bindIncidentMapper(incidentMapperImpl: IncidentMapperImpl): IncidentMapper

	@Binds
	fun bindVisitMapper(visitMapperImpl: VisitMapperImpl): VisitMapper
}