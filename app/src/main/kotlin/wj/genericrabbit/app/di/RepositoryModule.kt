package wj.genericrabbit.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wj.genericrabbit.app.data.repository.AttendeesRepositoryImpl
import wj.genericrabbit.app.data.repository.IncidentsRepositoryImpl
import wj.genericrabbit.app.data.repository.VisitsRepositoryImpl
import wj.genericrabbit.app.domain.repository.AttendeesRepository
import wj.genericrabbit.app.domain.repository.IncidentsRepository
import wj.genericrabbit.app.domain.repository.VisitsRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

	@Binds
	@Singleton
	fun bindAttendeesRepository(attendeesRepositoryImpl: AttendeesRepositoryImpl): AttendeesRepository

	@Binds
	@Singleton
	fun bindIncidentsRepository(incidentsRepositoryImpl: IncidentsRepositoryImpl): IncidentsRepository

	@Binds
	@Singleton
	fun bindIncidentsRepository(visitsRepositoryImpl: VisitsRepositoryImpl): VisitsRepository
}