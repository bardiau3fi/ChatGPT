package com.example.chatgpt

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.chatgpt.databinding.ActivityMainBinding
import com.example.chatgpt.utils.NetworkConnectivityObserver
import com.example.chatgpt.utils.NetworkStatus
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var _binding:ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val networkConnectivityObserver: NetworkConnectivityObserver by lazy {
        NetworkConnectivityObserver(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        val splashScreen=installSplashScreen()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        splashScreen.setKeepOnScreenCondition{false}
/*
        val snackbar = Snackbar.make(
            findViewById(com.google.android.material.R.id.content),
            "No Internet Connection",
            Snackbar.LENGTH_INDEFINITE
        ).setAction("Wifi") {
            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        }

        networkConnectivityObserver.observe(this) {
            when (it) {
                NetworkStatus.Available -> {
                    if (snackbar.isShown) {
                        snackbar.dismiss()
                    }
                }
                else -> {

                    snackbar.show()
                }
            }
        }
*/


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}