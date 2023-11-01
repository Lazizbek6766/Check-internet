package uz.megasoft.button

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkInternet.observe(this, Observer { isInternetEnabled ->
            if (isInternetEnabled) {
                // Internet ulanish mavjud
                Toast.makeText(this, "Internet ulanish bor", Toast.LENGTH_SHORT).show()
            } else {
                // Internet ulanishi yo'q
                Toast.makeText(this, "Internet ulanish yo'q", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        // BroadcastReceiver'ni aktivatsiyalash
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(checkInternet.internetSwitchStateReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        // BroadcastReceiver'ni o'chirish
        unregisterReceiver(checkInternet.internetSwitchStateReceiver)
    }
}