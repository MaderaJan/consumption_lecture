package cz.muni.consumption.ui.trends

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cz.muni.consumption.databinding.ItemMeasuredConsumptionBinding
import cz.muni.consumption.databinding.ItemTrendBinding
import cz.muni.consumption.ui.consumption.MeasuredConsumptionViewHolder
import cz.muni.consumption.ui.data.MeasuredConsumption
import cz.muni.consumption.ui.data.TrendData
import cz.muni.consumption.ui.util.DateUtil
import cz.muni.consumption.ui.util.oneDecimal

// TODO Hint: formátoví čísel pomocí extension oneDecimal
// TODO Hint: formátování datumu DateUtil.yearMonthFormat.format(item.date)
// TODO Hint: Trend differ areItemsTheSame -> date

class TrendsAdapter() : ListAdapter<TrendData, TrendDataViewHolder> {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendDataViewHolder =
        TrendDataViewHolder(
            ItemTrendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: TrendDataViewHolder, position: Int) {
//        val item = getItem(position)
//        holder.bind(item, onItemClick)
    }
}

class TrendDataViewHolder(private val binding: ItemTrendBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: TrendData) {
        binding.dateTextView.text = DateUtil.yearMonthFormat.format(item.date)
        binding.consumptionTextView.text = item.consumption.oneDecimal()
        binding.temperatureTextView.text = (item.temperature?.oneDecimal() + "°C") ?: "Unknown"
        binding.priceTextView.text = item.price.oneDecimal() + "Kc"
    }
}

class TrendDataDiffUtil : DiffUtil.ItemCallback<TrendData>() {
    override fun areItemsTheSame(oldItem: TrendData, newItem: TrendData): Boolean =
        oldItem.date == newItem.date

    override fun areContentsTheSame(oldItem: TrendData, newItem: TrendData): Boolean =
        oldItem == newItem
}