package pt.isel.pdm.factreminder

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast

class FactService : IntentService("Fact") {
    override fun onHandleIntent(intent: Intent?) {
        Log.i("Facts/Service", "onHandleIntent entry")
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(this, "PDM is Great!", Toast.LENGTH_LONG).show()
        }
    }
}