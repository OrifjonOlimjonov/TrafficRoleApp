package uz.orifjon.trafficroleapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.orifjon.trafficroleapp.fragments.ViewPagerFragment

class ViewPagerAdapter(fragment: FragmentManager, lifecycle: Lifecycle):FragmentStateAdapter(fragment,lifecycle) {
    var list = arrayListOf(
        "Ogohlantiruvchi",
        "Imtiyozli",
        "Ta'qiqlovchi",
        "Buyuruvchi"
    )

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return ViewPagerFragment.newInstance(list[position],position)
    }
}