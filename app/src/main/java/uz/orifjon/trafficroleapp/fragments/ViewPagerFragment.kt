package uz.orifjon.trafficroleapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.orifjon.trafficroleapp.R
import uz.orifjon.trafficroleapp.adapters.RecyclerViewAdapter
import uz.orifjon.trafficroleapp.database.Role
import uz.orifjon.trafficroleapp.database.RoleDatabase
import uz.orifjon.trafficroleapp.databinding.FragmentViewPagerBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ViewPagerFragment : Fragment() {
    private var nameFragment: String? = null
    private var position: Int? = null
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var list: ArrayList<Role>
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
    ): View {
        list = ArrayList()
        binding = FragmentViewPagerBinding.inflate(inflater)
        list = RoleDatabase.getDatabase(requireContext()).roleDao()
            .listRoleByType(nameFragment!!) as ArrayList

        if (list.isEmpty()) {
            binding.tvToast.visibility = View.VISIBLE
        } else {
            binding.tvToast.visibility = View.INVISIBLE
        }
        adapter = RecyclerViewAdapter(requireContext(),list, { role, i ->
            RoleDatabase.getDatabase(requireContext()).roleDao().deleteRole(role)
            list.removeAt(i)
            adapter.notifyItemRemoved(i)
            adapter.notifyItemRangeChanged(i, list.size)
            if (list.isEmpty()) {
                binding.tvToast.visibility = View.VISIBLE
            } else {
                binding.tvToast.visibility = View.INVISIBLE
            }
        }, { role, i ->
            val bundle = Bundle()
            bundle.putSerializable("role", role)
            findNavController().navigate(R.id.editRoleFragment, bundle)
            RoleDatabase.getDatabase(requireContext()).roleDao().editRole(role)
        },{role, i ->
            if(role.isLiked == 1){
                role.isLiked = 0
            }else{
                role.isLiked = 1
            }
            RoleDatabase.getDatabase(requireContext()).roleDao().editRole(role)
            list[i] = role
            adapter.notifyItemChanged(i)
        })
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