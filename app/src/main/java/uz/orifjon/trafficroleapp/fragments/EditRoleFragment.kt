package uz.orifjon.trafficroleapp.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.rotationMatrix
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.orifjon.trafficroleapp.R
import uz.orifjon.trafficroleapp.adapters.SpinnerAdapter
import uz.orifjon.trafficroleapp.database.Role
import uz.orifjon.trafficroleapp.database.RoleDatabase
import uz.orifjon.trafficroleapp.databinding.FragmentEditRoleBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EditRoleFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentEditRoleBinding
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var adapter: SpinnerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditRoleBinding.inflate(inflater)
        bottomNavigation = requireActivity().findViewById(R.id.bottomnavigation)
        bottomNavigation.visibility = View.GONE
        adapter = SpinnerAdapter()
        var list = arrayListOf(
            "Qaysi turga kirishi",
            "Ogohlantiruvchi",
            "Imtiyozli",
            "Ta'qiqlovchi",
            "Buyuruvchi"
        )
        val role = arguments?.getSerializable("role") as Role
        var position = -1
        binding.spinner.adapter = adapter
        binding.editText1.setText(role.roleName)
        binding.editText2.setText(role.roleDescription)
        binding.imageSymbol.setImageBitmap(
            BitmapFactory.decodeByteArray(
                role.bitmap,
                0,
                role.bitmap.size
            )
        )
        binding.imageSymbol.setOnClickListener {
            takePhotoResult.launch("image/*")
        }
        for (i in 0 until list.size) {
            if (list[i] == role.typeRole) {
                position = i
            }
        }
        binding.spinner.setSelection(position)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSave.setOnClickListener {
            if (binding.editText1.text.toString().isNotEmpty() && binding.editText2.text.toString()
                    .isNotEmpty() && binding.spinner.selectedItemPosition > 0
            ) {
                val name = binding.editText1.text.toString()
                val description = binding.editText2.text.toString()
                val type = binding.spinner.selectedItem.toString()
                val bitmap = getBitmapFromView(binding.imageSymbol)
                val byteStream = ByteArrayOutputStream()
                bitmap!!.compress(Bitmap.CompressFormat.PNG,100,byteStream)
                val byteArray = byteStream.toByteArray()
                val role1 = Role(
                    id = role.id,
                    roleName = name,
                    roleDescription = description,
                    typeRole = type,
                    isLiked = role.isLiked,
                    bitmap = byteArray
                )
                RoleDatabase.getDatabase(requireContext()).roleDao().editRole(role1)
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Maydonlarni to'ldiring!!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        return binding.root
    }

    fun getBitmapFromView(view: View): Bitmap? {
        val bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
    private val takePhotoResult = registerForActivityResult(ActivityResultContracts.GetContent()){ uri->
        if(uri ==null) return@registerForActivityResult
        binding.imageSymbol.setImageURI(uri)
        val openInputStream = requireActivity().contentResolver?.openInputStream(uri)
        val file = File(requireActivity().filesDir,"face.png")
        val fileOutputStream = FileOutputStream(file)
        openInputStream?.copyTo(fileOutputStream)
        openInputStream?.close()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bottomNavigation.visibility = View.VISIBLE
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditRoleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}