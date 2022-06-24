package wj.genericrabbit.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.ListItemBinding

class ObjectListAdapter : ListAdapter<Object, ObjectListAdapter.ObjectViewHolder>(ObjectDiffCallback) {

	class ObjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		private val binding = ListItemBinding.bind(itemView)

		fun bind(data: Object) {
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
		return ObjectViewHolder(view)
	}

	override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
		val obj = getItem(position)
		holder.bind(obj)
	}
}

object ObjectDiffCallback : DiffUtil.ItemCallback<Object>() {
	override fun areItemsTheSame(oldItem: Object, newItem: Object) = oldItem.id == newItem.id
	override fun areContentsTheSame(oldItem: Object, newItem: Object) = oldItem == newItem
}

data class Object(val id: Int, val content: Any)