package pt.isel.jhht.pdm.profs

import android.app.Application
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class ProfsApp : Application() {

    val requestQueue by lazy { Volley.newRequestQueue(this) }

    override fun onCreate() {
        super.onCreate()
        Log.d("Profs/App", "ProfsApp onCreate")
    }
}

val Application.requestQueue : RequestQueue
    get() = (this as ProfsApp).requestQueue
