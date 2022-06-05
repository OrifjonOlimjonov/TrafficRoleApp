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
import uz.orifjon.trafficroleapp.databinding.FragmentLikeBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LikeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentLikeBinding
    private lateinit var list: ArrayList<Role>
    private lateinit var adapter: RecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikeBinding.inflate(inflater)
        list = RoleDatabase.getDatabase(requireContext()).roleDao().listIsLiked() as ArrayList<Role>

        if (list.isNotEmpty()) {
            binding.tvToast.visibility = View.INVISIBLE
        } else {
            binding.tvToast.visibility = View.VISIBLE
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
                list.removeAt(i)
                adapter.notifyItemRemoved(i)
                adapter.notifyItemRangeChanged(i,list.size)
            }else{
                role.isLiked = 1
            }
            RoleDatabase.getDatabase(requireContext()).roleDao().editRole(role)

            if (list.isNotEmpty()) {
                binding.tvToast.visibility = View.INVISIBLE
            } else {
                binding.tvToast.visibility = View.VISIBLE
            }
        },{role, i ->
            val bundle = Bundle()
            bundle.putSerializable("role",role)
            findNavController().navigate(R.id.itemInfoFragment,bundle)
        })
        binding.rv.adapter = adapter

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LikeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}