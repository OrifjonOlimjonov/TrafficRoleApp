package uz.orifjon.trafficroleapp.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.orifjon.trafficroleapp.R
import uz.orifjon.trafficroleapp.adapters.SpinnerAdapter
import uz.orifjon.trafficroleapp.database.Role
import uz.orifjon.trafficroleapp.database.RoleDatabase
import uz.orifjon.trafficroleapp.databinding.FragmentAddRoleBinding
import java.io.ByteArrayOutputStream


class AddRoleFragment : Fragment() {

    private lateinit var binding: FragmentAddRoleBinding
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var spinnerAdapter: SpinnerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddRoleBinding.inflate(inflater)
        bottomNavigation = requireActivity().findViewById(R.id.bottomnavigation)
        bottomNavigation.visibility = View.GONE
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        spinnerAdapter = SpinnerAdapter()
        binding.spinner.adapter = spinnerAdapter

        binding.btnSave.setOnClickListener {
            if (binding.editText1.text.toString().isNotEmpty() && binding.editText2.text.toString()
                    .isNotEmpty() && binding.spinner.selectedItemPosition > 0
            ) {
                val name = binding.editText1.text.toString()
                val description = binding.editText2.text.toString()
                val type = binding.spinner.selectedItem.toString()
                val bitmap = (binding.imageSymbol.drawable as BitmapDrawable).bitmap
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
                val image = stream.toByteArray()
                val role = Role(
                    roleName = name,
                    roleDescription = description,
                    typeRole = type,
                    isLiked = 0,
                    bitmap = image
                )
                RoleDatabase.getDatabase(requireContext()).roleDao().addRole(role)
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Maydonlarni to'ldiring!!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        bottomNavigation.visibility = View.VISIBLE
    }

}