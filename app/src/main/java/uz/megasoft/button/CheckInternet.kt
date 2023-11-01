package uz.megasoft.button

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData

@Suppress("DEPRECATION")
class CheckInternet: LiveData<Boolean>() {
    val internetSwitchStateReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            val isInternetEnabled = activeNetwork != null && activeNetwork.isConnected
            postValue(isInternetEnabled)
        }
    }
}