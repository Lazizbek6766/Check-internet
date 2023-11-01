package uz.megasoft.button

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import uz.megasoft.button.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val checkInternet: CheckInternet by lazy {
        CheckInternet()
    }

    private val networkMonitor = NetworkMonitorUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.Wifi -> {
                                Log.d("TAG", "onCreate: wifi")

                            }
                            ConnectionType.Cellular -> {
                                Log.d("TAG", "onCreate: internet")
                            }
                            else -> { }
                        }
                    }
                    false -> {
                        Log.d("TAG", "onCreate: yo'q")
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStart() {
        super.onStart()
        // BroadcastReceiver'ni aktivatsiyalash
//        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
//        registerReceiver(checkInternet.internetSwitchStateReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()

        networkMonitor.unregister()

        // BroadcastReceiver'ni o'chirish
//        unregisterReceiver(checkInternet.internetSwitchStateReceiver)
    }
}