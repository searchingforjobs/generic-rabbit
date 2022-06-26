package wj.genericrabbit.app.domain.usecase

import wj.genericrabbit.app.domain.model.Visit
import wj.genericrabbit.app.domain.repository.VisitsRepository
import javax.inject.Inject

interface GetAllVisitsUseCase {
	suspend operator fun invoke(): List<Visit>
}

class GetAllVisitsUseCaseImpl @Inject constructor(private val visitsRepository: VisitsRepository) :
	GetAllVisitsUseCase {

	override suspend fun invoke() = visitsRepository.getAllVisits()
}