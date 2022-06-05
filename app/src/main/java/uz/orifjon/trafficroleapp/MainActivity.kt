package uz.orifjon.trafficroleapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_main.*
import uz.orifjon.trafficroleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener{
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_TrafficRoleApp)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomnavigation.setOnItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
              findNavController(binding.fragmentContainerView).navigate(R.id.mainFragment)
                return true
            }
            R.id.like -> {
                findNavController(binding.fragmentContainerView).navigate(R.id.likeFragment)
                return true
            }
            R.id.info -> {
                findNavController(binding.fragmentContainerView).navigate(R.id.infoFragment)
                return true
            }
        }
        return false
    }
}