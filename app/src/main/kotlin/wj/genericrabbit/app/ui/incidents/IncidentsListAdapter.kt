package wj.genericrabbit.app.ui.incidents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.IncidentItemBinding
import wj.genericrabbit.app.domain.model.Incident
import wj.genericrabbit.app.domain.model.shortName
import java.time.format.DateTimeFormatter

class IncidentsListAdapter : ListAdapter<Incident, IncidentsListAdapter.IncidentViewHolder>(ObjectDiffCallback) {

	class IncidentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		private val binding = IncidentItemBinding.bind(itemView)

		fun bind(data: Incident) {
			binding.textViewName.text = data.shortName
			if (data.shortName.isBlank()) {
				binding.textViewName.setText(R.string.unknown)
			}
			binding.textViewDate.text = data.createdAt.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"))
			binding.buttonDetails.setOnClickListener {
				val toIncidentDetails = IncidentsFragmentDirections.incidentsToDetailsFragment(data.id)
				binding.root.findNavController().navigate(toIncidentDetails)
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.incident_item, parent, false)
		return IncidentViewHolder(view)
	}

	override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
		val obj = getItem(position)
		holder.bind(obj)
	}
}

object ObjectDiffCallback : DiffUtil.ItemCallback<Incident>() {
	override fun areItemsTheSame(oldItem: Incident, newItem: Incident) = oldItem.id == newItem.id
	override fun areContentsTheSame(oldItem: Incident, newItem: Incident) = oldItem == newItem
}