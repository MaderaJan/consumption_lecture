package cz.muni.consumption.ui.consumption

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.muni.consumption.databinding.ItemMeasuredConsumptionBinding
import cz.muni.consumption.ui.data.MeasuredConsumption

class ConsumptionAdapter(
    private val onItemClick: (MeasuredConsumption) -> Unit,
) : ListAdapter<MeasuredConsumption, MeasuredConsumptionViewHolder>(MeasuredConsumptionDiffUtil()) {

    // TODO 5.1 Class ListAdapter & Generic Types MeasuredConsumption, MeasuredConsumptionViewHolder
    // TODO 5.2 Differ MeasuredConsumptionDiffUtil

    // TODO 5.3 Binding ViewHolder -> ItemMeasuredConsumptionBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasuredConsumptionViewHolder =
        MeasuredConsumptionViewHolder(ItemMeasuredConsumptionBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    // TODO 5.4 onBindViewHolder
    override fun onBindViewHolder(holder: MeasuredConsumptionViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClick)
    }
}

class MeasuredConsumptionViewHolder(
    private val binding: ItemMeasuredConsumptionBinding
) : RecyclerView.ViewHolder(binding.root) { // TODO 6.1 ViewHolder: RecyclerView.ViewHolder

    fun bind(item: MeasuredConsumption, onItemClick: (MeasuredConsumption) -> Unit) {
        // TODO 11.1 Postupný binding Views
        // TODO 11.1 (S) item.getConsumptionText()
        // TODO 11.1 (S) DateUtil.dateFormat.format(item.measurementDate)
        // TODO 11.1 (S) item.type.name
        // TODO 11.1 (S) ContextCompat.getColorStateList(itemView.context, item.type.geColor())

        // TODO 11.2 (S) setOnClickListener + callback -> ?hint: onItemClick()
    }
}

// TODO 7 MeasuredConsumptionDiffUtil
class MeasuredConsumptionDiffUtil : DiffUtil.ItemCallback<MeasuredConsumption>() {

    override fun areItemsTheSame(oldItem: MeasuredConsumption, newItem: MeasuredConsumption): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MeasuredConsumption, newItem: MeasuredConsumption): Boolean =
        oldItem == newItem

}