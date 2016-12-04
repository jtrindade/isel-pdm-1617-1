package pt.isel.pdm.factreminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class EventsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.i("Facts/Receiver", "onReceive entry")

        if (!Intent.ACTION_BOOT_COMPLETED.equals(intent?.action)) {
            context.startService(Intent(context, FactService::class.java))
        }
    }
}
