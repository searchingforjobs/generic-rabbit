package wj.genericrabbit.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import wj.genericrabbit.app.domain.usecase.*

@InstallIn(ViewModelComponent::class)
@Module
interface UseCaseModule {

	@Binds
	fun bindGetAttendeeByIdUseCase(getAttendeeByIdUseCaseImpl: GetAttendeeByIdUseCaseImpl): GetAttendeeByIdUseCase

	@Binds
	fun bindGetAttendeesByPhotoUseCase(getAttendeesByPhotoUseCaseImpl: GetAttendeesByPhotoUseCaseImpl): GetAttendeesByPhotoUseCase

	@Binds
	fun bindGetAllIncidentsUseCase(getAllIncidentsUseCaseImpl: GetAllIncidentsUseCaseImpl): GetAllIncidentsUseCase

	@Binds
	fun bindGetAllVisitsUseCase(getAllVisitsUseCaseImpl: GetAllVisitsUseCaseImpl): GetAllVisitsUseCase

	@Binds
	fun bindCreateVisitUseCase(createVisitUseCaseImpl: CreateVisitUseCaseImpl): CreateVisitUseCase
}