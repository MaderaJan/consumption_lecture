package cz.muni.consumption.ui.consumption

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.muni.consumption.databinding.ItemMeasuredConsumptionBinding
import cz.muni.consumption.ui.data.MeasuredConsumption
import cz.muni.consumption.ui.util.DateUtil

class ConsumptionAdapter(
    private val onItemClick: (MeasuredConsumption) -> Unit,
) : ListAdapter<MeasuredConsumption, MeasuredConsumptionViewHolder>(MeasuredConsumptionDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasuredConsumptionViewHolder =
        MeasuredConsumptionViewHolder(
            ItemMeasuredConsumptionBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MeasuredConsumptionViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClick)
    }
}

class MeasuredConsumptionViewHolder(
    private val binding: ItemMeasuredConsumptionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MeasuredConsumption, onItemClick: (MeasuredConsumption) -> Unit) {
        binding.consumptionTextView.text = item.getConsumptionText()
        binding.dateTextView.text = DateUtil.dateFormat.format(item.measurementDate)
        binding.typeChip.text = item.type.name
        binding.typeChip.chipBackgroundColor = ContextCompat.getColorStateList(itemView.context, item.type.geColor())

        binding.root.setOnClickListener {
            onItemClick(item)
        }
    }
}

class MeasuredConsumptionDiffUtil : DiffUtil.ItemCallback<MeasuredConsumption>() {

    override fun areItemsTheSame(oldItem: MeasuredConsumption, newItem: MeasuredConsumption): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MeasuredConsumption, newItem: MeasuredConsumption): Boolean =
        oldItem == newItem

}