package uz.orifjon.trafficroleapp.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.orifjon.trafficroleapp.R
import uz.orifjon.trafficroleapp.database.Role
import uz.orifjon.trafficroleapp.databinding.FragmentItemInfoBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class ItemInfoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private lateinit var binding:FragmentItemInfoBinding
    private lateinit var bottomNavigation: BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemInfoBinding.inflate(inflater)

        val role = arguments?.getSerializable("role") as Role
        binding.toolbar.title = role.roleName
        bottomNavigation = requireActivity().findViewById(R.id.bottomnavigation)
        bottomNavigation.visibility = View.GONE
        binding.tvTheme.text = role.roleName
        binding.tvInfo.text = role.roleDescription
        binding.imgSysmbol.setImageBitmap(
            BitmapFactory.decodeByteArray(
                role.bitmap,
                0,
                role.bitmap.size
            )
        )
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bottomNavigation.visibility = View.VISIBLE
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}