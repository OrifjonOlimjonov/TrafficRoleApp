package uz.orifjon.trafficroleapp.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import uz.orifjon.trafficroleapp.R
import uz.orifjon.trafficroleapp.adapters.ViewPagerAdapter
import uz.orifjon.trafficroleapp.databinding.FragmentMainBinding
import uz.orifjon.trafficroleapp.databinding.ViewpagerItemBinding


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var itemBinding: ViewpagerItemBinding
    private val list = arrayListOf(
        "Ogohlantiruvchi",
        "Imtiyozli",
        "Ta'qiqlovchi",
        "Buyuruvchi"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater)
        adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewpager2.adapter = adapter
        TabLayoutMediator(binding.tablayout, binding.viewpager2) { tab, position ->
            itemBinding = ViewpagerItemBinding.inflate(inflater)
            itemBinding.tvTab.text = list[position]
            if (position == 0) {
                itemBinding.tvTab.setTextColor(Color.parseColor("#005CA1"))
                itemBinding.tabBackground.setBackgroundResource(R.drawable.select_viewpager_item)
            } else {
                itemBinding.tabBackground.setBackgroundResource(R.drawable.unselect_viewpager_item)
            }
            tab.customView = itemBinding.root
        }.attach()

        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val bind = ViewpagerItemBinding.bind(tab!!.customView!!)
                bind.tvTab.setTextColor(Color.parseColor("#005CA1"))
                bind.tabBackground.setBackgroundResource(R.drawable.select_viewpager_item)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val bind= ViewpagerItemBinding.bind(tab!!.customView!!)
                bind.tvTab.setTextColor(Color.WHITE)
                bind.tabBackground.setBackgroundResource(R.drawable.unselect_viewpager_item)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })


        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.btnAdd -> {
                    findNavController().navigate(R.id.addRoleFragment)
                }
            }
            true
        }


        return binding.root
    }

}

