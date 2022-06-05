package uz.orifjon.trafficroleapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import uz.orifjon.trafficroleapp.databinding.SpinnerItemBinding


class SpinnerAdapter : BaseAdapter() {
    var list = arrayListOf(
        "Qaysi turga kirishi",
        "Ogohlantiruvchi",
        "Imtiyozli",
        "Ta'qiqlovchi",
        "Buyuruvchi"
    )

    override fun getCount(): Int = 5

    override fun getItem(position: Int): String = list[position]

    override fun getItemId(position: Int): Long = position.toLong()


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding: SpinnerItemBinding = if (p1 == null) {
            SpinnerItemBinding.inflate(LayoutInflater.from(p2?.context), p2, false)
        } else {
            SpinnerItemBinding.bind(p1)
        }
        if (p0 == 0) {
            binding.spinText.setTextColor(Color.parseColor("#A8A3A3"))
        } else {
            binding.spinText.setTextColor(Color.BLACK)
        }
        binding.spinText.text = list[p0]
        return binding.root
    }

    override fun isEnabled(position: Int): Boolean {
        return position != 0
    }


}