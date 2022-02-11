package wiki.scene.mvidemo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import wiki.scene.mvidemo.R
import wiki.scene.mvidemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
    }

    private fun initView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
//        navController = findNavController(R.id.nav_host_container)
        navController = navHostFragment.navController
        mBinding.navView.setupWithNavController(navController)
//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//
//        }
    }

}