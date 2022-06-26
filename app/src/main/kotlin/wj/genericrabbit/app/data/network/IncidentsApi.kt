package wj.genericrabbit.app.data.network

import kotlinx.coroutines.delay
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.internal.toImmutableList
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import wj.genericrabbit.app.data.network.model.IncidentDto
import kotlin.time.Duration.Companion.seconds

interface IncidentsApi {

	@GET("incidents")
	suspend fun getAllIncidents(): List<IncidentDto>

	@Multipart
	@POST("incidents")
	suspend fun createIncident(
		@Part file: MultipartBody.Part,
		@Part("securityId") securityId: RequestBody,
		@Part("firstname") firstName: RequestBody,
		@Part("middlename") middleName: RequestBody,
		@Part("lastname") lastName: RequestBody,
		@Part("description") description: RequestBody
	)
}

class IncidentsApiMock : IncidentsApi {

	private val list = listOf(
		IncidentDto(
			"0",
			null,
			"Андрей",
			"Владимирович",
			"Королёв",
			"",
			"Мужчина пришёл забирать ребёнка, сказал, что всё знает о системе, но так и не зарегистрировался.",
			"2022-06-15T21:35:09.909",
			"2022-06-15T21:35:09.909"
		),
		IncidentDto(
			"1",
			null,
			"Ольга",
			"Валерьевна",
			"Носова",
			"",
			"Приходила женщина, хотела попасть в здание школы, не могла объяснить что ей нужно.",
			"2022-06-16T21:35:09.909",
			"2022-06-16T21:35:09.909"
		),
		IncidentDto(
			"2",
			null,
			"",
			"",
			"",
			"",
			"Посторонний.",
			"2022-06-20T21:35:09.909",
			"2022-06-20T21:35:09.909"
		),
		IncidentDto(
			"3",
			null,
			"",
			"",
			"",
			"",
			"Зевака.",
			"2022-06-25T21:35:09.909",
			"2022-06-25T21:35:09.909"
		),
	)

	override suspend fun getAllIncidents(): List<IncidentDto> {
		delay(1.seconds)
		return list.toImmutableList()
	}

	override suspend fun createIncident(
		file: MultipartBody.Part,
		securityId: RequestBody,
		firstName: RequestBody,
		middleName: RequestBody,
		lastName: RequestBody,
		description: RequestBody
	) {
	}

}