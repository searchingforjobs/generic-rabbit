package wj.genericrabbit.app.domain.model

import java.io.Serializable

sealed interface IdentificationData : Serializable {
	data class QR(val attendeeId: String) : IdentificationData
	data class FacePhoto(val photoPath: String) : IdentificationData
}