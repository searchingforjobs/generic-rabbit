package wj.genericrabbit.app.ui.incidents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.BottomSheetIncidentDetailsBinding
import wj.genericrabbit.app.domain.model.shortName
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class IncidentDetailsFragment : BottomSheetDialogFragment() {

	private var _binding: BottomSheetIncidentDetailsBinding? = null
	private val args by navArgs<IncidentDetailsFragmentArgs>()

	private val binding: BottomSheetIncidentDetailsBinding
		get() = _binding!!

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_binding = BottomSheetIncidentDetailsBinding.inflate(inflater)
		return _binding?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		args.incident.run {
			if (!photoUrl.isNullOrBlank()) {
				binding.imageViewIncidentPhoto.load(photoUrl) {
					crossfade(true)
				}
			}
			if (shortName.isNotBlank()) {
				binding.textViewIncidentName.text = shortName
			}
			if (shortName.isNotBlank()) {
				binding.textViewIncidentDescription.text = description
			}
			binding.textViewIncidentDateTime.text = getString(
				R.string.incident_date_time,
				createdAt.format(DateTimeFormatter.ofPattern("dd.MM.uuuu HH:mm"))
			)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}