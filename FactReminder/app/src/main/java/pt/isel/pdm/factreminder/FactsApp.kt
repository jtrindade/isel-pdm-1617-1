package pt.isel.pdm.factreminder

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log

class FactsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i("Facts/App", "onCreate entry")

        val alarmIntent = Intent(this, EventsReceiver::class.java)

        if (PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_NO_CREATE) == null) {
            Log.i("Facts/App", "no alarm")

            val pendingAlarmIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0)

            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

            alarmManager.setInexactRepeating(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + 8*1000,
                    8*1000,
                    pendingAlarmIntent
            )
        }
    }
}