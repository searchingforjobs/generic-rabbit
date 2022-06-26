package wj.genericrabbit.app.ui.identification.similar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.FragmentSimilarAttendeesBinding

@AndroidEntryPoint
class SimilarAttendeesFragment : Fragment(R.layout.fragment_similar_attendees) {

	private val binding by viewBinding(FragmentSimilarAttendeesBinding::bind)
	private val args by navArgs<SimilarAttendeesFragmentArgs>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		val adapter = AttendeesListAdapter()
		binding.recyclerViewSimilarAttendees.adapter = adapter
		adapter.submitList(args.attendees.asList())
		binding.buttonSimilarAttendeesIncident.setOnClickListener {
			val toCreateIncidentDialog =
				SimilarAttendeesFragmentDirections.similarToCreateIncidentFragment(args.photoUrl)
			findNavController().navigate(toCreateIncidentDialog)
		}
	}
}