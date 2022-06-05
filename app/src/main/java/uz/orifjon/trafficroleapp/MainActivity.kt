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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomnavigation.setOnItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.home -> {
//              findNavController(binding.fragmentContainerView).navigate(R.id.homeFragment)
//                return true
//            }
//            R.id.mark -> {
//                findNavController(binding.fragmentContainerView).navigate(R.id.markFragment)
//                return true
//            }
//            R.id.setting -> {
//                findNavController(binding.fragmentContainerView).navigate(R.id.settingsFragment)
//                return true
//            }
//            R.id.notification -> {
//                findNavController(binding.fragmentContainerView).navigate(R.id.notificationFragment)
//                return true
//            }
        }
        return false
    }
}