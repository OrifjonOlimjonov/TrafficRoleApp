package uz.orifjon.trafficroleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.orifjon.trafficroleapp.adapters.SpinnerAdapter
import uz.orifjon.trafficroleapp.R
import uz.orifjon.trafficroleapp.databinding.FragmentAddRoleBinding


class AddRoleFragment : Fragment() {

    private lateinit var binding: FragmentAddRoleBinding
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var spinnerAdapter:SpinnerAdapter
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


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        bottomNavigation.visibility = View.VISIBLE
    }

}