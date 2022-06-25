package wj.genericrabbit.app.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import wj.genericrabbit.app.data.network.*
import wj.genericrabbit.app.data.network.converter.NullOnEmptyConverterFactory
import javax.inject.Singleton

private val BASE_URL = "http://84.201.178.199"

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

	@Provides
	@Singleton
	fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
		.baseUrl(BASE_URL)
		.addConverterFactory(NullOnEmptyConverterFactory())
		.addConverterFactory(MoshiConverterFactory.create(moshi))
		.build()

	@Provides
	@Singleton
	fun provideMoshi(): Moshi = Moshi.Builder()
		.addLast(KotlinJsonAdapterFactory())
		.build()

	@Provides
	@Singleton
	fun provideAttendeesApi(retrofit: Retrofit): AttendeesApi = retrofit.create()

	@Provides
	@Singleton
	fun provideIncidentsApi(retrofit: Retrofit): IncidentsApi = IncidentsApiMock()

	@Provides
	@Singleton
	fun provideVisitsApi(retrofit: Retrofit): VisitsApi = retrofit.create()

	@Provides
	@Singleton
	fun provideFaceRecognitionApi(retrofit: Retrofit): FaceRecognitionApi = retrofit.create()
}