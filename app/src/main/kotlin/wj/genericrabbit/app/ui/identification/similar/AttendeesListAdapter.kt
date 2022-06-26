package wj.genericrabbit.app.ui.identification.similar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.AttendeeItemBinding
import wj.genericrabbit.app.domain.model.Attendee
import wj.genericrabbit.app.domain.model.fullName

class AttendeesListAdapter : ListAdapter<Attendee, AttendeesListAdapter.AttendeeViewHolder>(AttendeeDiffCallback) {

	class AttendeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		private val binding = AttendeeItemBinding.bind(itemView)

		fun bind(data: Attendee) {
			if (data.fullName.isNotBlank()) {
				binding.textViewAttendeeName.text = data.fullName
			} else {
				binding.textViewAttendeeName.setText(R.string.unknown)
			}
			binding.imageViewAttendeePhoto.load(data.photoUrl) {
				crossfade(true)
			}
			binding.buttonAttendeeConfirmation.setOnClickListener {
				val toSuccessScreen = SimilarAttendeesFragmentDirections.similarToSuccessFragment(data)
				binding.root.findNavController().navigate(toSuccessScreen)
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendeeViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.attendee_item, parent, false)
		return AttendeeViewHolder(view)
	}

	override fun onBindViewHolder(holder: AttendeeViewHolder, position: Int) {
		val obj = getItem(position)
		holder.bind(obj)
	}
}

object AttendeeDiffCallback : DiffUtil.ItemCallback<Attendee>() {
	override fun areItemsTheSame(oldItem: Attendee, newItem: Attendee) = oldItem.id == newItem.id
	override fun areContentsTheSame(oldItem: Attendee, newItem: Attendee) = oldItem == newItem
}