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

class IncidentsListAdapter : ListAdapter<Incident, IncidentsListAdapter.IncidentViewHolder>(IncidentDiffCallback) {

	class IncidentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		private val binding = IncidentItemBinding.bind(itemView)

		fun bind(data: Incident) {
			if (data.shortName.isNotBlank()) {
				binding.textViewNameIncident.text = data.shortName
			} else {
				binding.textViewNameIncident.setText(R.string.unknown)
			}
			binding.textViewDateIncident.text = data.createdAt.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"))
			binding.buttonDetailsIncident.setOnClickListener {
				val toIncidentDetails = IncidentsFragmentDirections.incidentsToDetailsFragment(data)
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

object IncidentDiffCallback : DiffUtil.ItemCallback<Incident>() {
	override fun areItemsTheSame(oldItem: Incident, newItem: Incident) = oldItem.id == newItem.id
	override fun areContentsTheSame(oldItem: Incident, newItem: Incident) = oldItem == newItem
}