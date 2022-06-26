package wj.genericrabbit.app.ui.visits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.VisitItemBinding
import wj.genericrabbit.app.domain.model.Visit
import wj.genericrabbit.app.domain.model.shortName
import java.time.format.DateTimeFormatter

class VisitsListAdapter : ListAdapter<Visit, VisitsListAdapter.VisitViewHolder>(VisitDiffCallback) {

	class VisitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		private val binding = VisitItemBinding.bind(itemView)

		fun bind(data: Visit) {
			if (data.attendee.shortName.isNotBlank()) {
				binding.textViewNameVisit.text = data.attendee.shortName
			} else {
				binding.textViewNameVisit.setText(R.string.unknown)
			}
			binding.textViewDateVisit.text = data.createdAt.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"))
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.visit_item, parent, false)
		return VisitViewHolder(view)
	}

	override fun onBindViewHolder(holder: VisitViewHolder, position: Int) {
		val obj = getItem(position)
		holder.bind(obj)
	}
}

object VisitDiffCallback : DiffUtil.ItemCallback<Visit>() {
	override fun areItemsTheSame(oldItem: Visit, newItem: Visit) = oldItem.id == newItem.id
	override fun areContentsTheSame(oldItem: Visit, newItem: Visit) = oldItem == newItem
}