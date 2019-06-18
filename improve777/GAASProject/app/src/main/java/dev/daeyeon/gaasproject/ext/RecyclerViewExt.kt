package dev.daeyeon.gaasproject.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.daeyeon.gaasproject.data.Ticker
import dev.daeyeon.gaasproject.ui.ticker.TickerAdapter

@BindingAdapter("items")
fun RecyclerView.setItems(items: List<Ticker>) {
    (adapter as? TickerAdapter)?.replaceList(items)
}