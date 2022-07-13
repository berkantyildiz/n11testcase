package com.n11.n11testcase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import com.n11.n11testcase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavHost {

    private val navigationController by lazy {
        checkNotNull((supportFragmentManager.findFragmentById(R.id.nav_app_host) as? NavHostFragment)?.navController)
    }

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(navigationController) {
            setGraph(R.navigation.nav_app)
            addOnDestinationChangedListener { _, destination, arguments ->
                Log.d(this.javaClass.simpleName, destination.label.toString())
            }
        }
    }
    override fun getNavController(): NavController = navigationController
}