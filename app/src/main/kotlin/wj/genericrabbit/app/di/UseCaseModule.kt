package wj.genericrabbit.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wj.genericrabbit.app.domain.usecase.GetAttendeeByIdUseCase
import wj.genericrabbit.app.domain.usecase.GetAttendeeByIdUseCaseImpl
import wj.genericrabbit.app.domain.usecase.GetAttendeesByPhotoUseCase
import wj.genericrabbit.app.domain.usecase.GetAttendeesByPhotoUseCaseImpl

@InstallIn(SingletonComponent::class)
@Module
interface UseCaseModule {

	@Binds
	fun bindGetAttendeeByIdUseCase(getAttendeeByIdUseCaseImpl: GetAttendeeByIdUseCaseImpl): GetAttendeeByIdUseCase

	@Binds
	fun bindGetAttendeesByPhotoUseCase(getAttendeesByPhotoUseCaseImpl: GetAttendeesByPhotoUseCaseImpl): GetAttendeesByPhotoUseCase
}