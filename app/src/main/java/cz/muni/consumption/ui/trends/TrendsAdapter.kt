package cz.muni.consumption.ui.trends

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.muni.consumption.databinding.ItemTrendBinding
import cz.muni.consumption.ui.data.TrendData
import cz.muni.consumption.ui.util.DateUtil
import cz.muni.consumption.ui.util.oneDecimal

class TrendsAdapter(
    private val onItemClick: (TrendData) -> Unit,
) : ListAdapter<TrendData, TrendViewHolder>(TrendDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder =
        TrendViewHolder(
            ItemTrendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClick)
    }
}

class TrendViewHolder(
    private val binding: ItemTrendBinding
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: TrendData, onItemClick: (TrendData) -> Unit) {
        binding.dateTextView.text = DateUtil.yearMonthFormat.format(item.date)
        binding.temperatureTextView.text = (item.temperature?.oneDecimal() + "°C")
        binding.consumptionTextView.text = item.consumption.oneDecimal() + "MWh"
        binding.priceTextView.text = item.price.oneDecimal() + "Euro"

        binding.root.setOnClickListener {
            onItemClick(item)
        }
    }
}

class TrendDiffUtil : DiffUtil.ItemCallback<TrendData>() {

    override fun areItemsTheSame(oldItem: TrendData, newItem: TrendData): Boolean =
        oldItem.date == newItem.date

    override fun areContentsTheSame(oldItem: TrendData, newItem: TrendData): Boolean =
        oldItem == newItem

}