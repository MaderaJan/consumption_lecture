package cz.muni.consumption.ui.trends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.muni.consumption.data.TrendData
import cz.muni.consumption.databinding.ItemTrendBinding
import cz.muni.consumption.util.DateUtil
import cz.muni.consumption.util.oneDecimal

class TrendAdapter : ListAdapter<TrendData, TrendViewHolder>(TrendDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder =
        TrendViewHolder(ItemTrendBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class TrendViewHolder(private val binding: ItemTrendBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TrendData) {
        binding.consumptionTextView.text = item.consumption.oneDecimal() + " ${item.type.getUnit()}"
        binding.priceTextView.text = item.price.oneDecimal() + " Kč"
        binding.dateTextView.text = DateUtil.yearMonthFormat.format(item.date)
        binding.temperatureTextView.text = if (item.temperature != null) {
            "${item.temperature.oneDecimal()} Celsius"
        } else {
            "- -"
        }
    }
}

class TrendDiffUtil : DiffUtil.ItemCallback<TrendData>() {

    override fun areItemsTheSame(oldItem: TrendData, newItem: TrendData): Boolean =
        oldItem.date.compareTo(newItem.date) == 0

    override fun areContentsTheSame(oldItem: TrendData, newItem: TrendData): Boolean =
        oldItem == newItem
}
