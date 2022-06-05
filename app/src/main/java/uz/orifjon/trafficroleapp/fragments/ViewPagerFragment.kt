package uz.orifjon.trafficroleapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.orifjon.trafficroleapp.R
import uz.orifjon.trafficroleapp.adapters.RecyclerViewAdapter
import uz.orifjon.trafficroleapp.adapters.ViewPagerAdapter
import uz.orifjon.trafficroleapp.database.Role
import uz.orifjon.trafficroleapp.database.RoleDatabase
import uz.orifjon.trafficroleapp.databinding.FragmentViewPagerBinding
import uz.orifjon.trafficroleapp.databinding.ViewpagerItemBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ViewPagerFragment : Fragment() {
    private var nameFragment: String? = null
    private var position: Int? = null
    private lateinit var adapter:RecyclerViewAdapter
    private lateinit var list: List<Role>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nameFragment = it.getString(ARG_PARAM1)
            position = it.getInt(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        list = ArrayList()
        binding = FragmentViewPagerBinding.inflate(inflater)
        list = RoleDatabase.getDatabase(requireContext()).roleDao().listRoleByType(nameFragment!!)

        if(list.isEmpty()){
            binding.tvToast.visibility = View.VISIBLE
        }else{
            binding.tvToast.visibility = View.INVISIBLE
        }
        adapter = RecyclerViewAdapter(list)
        binding.rv.adapter = adapter

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(nameFragment: String, position: Int) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, nameFragment)
                    putInt(ARG_PARAM2, position)
                }
            }
    }
}