package dev.weiqi.hichy

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*


object Hichy {

    val activityStack = Stack<Activity>()

    fun register(application: Application) {
        application.registerActivityLifecycleCallbacks(object :
            Application.ActivityLifecycleCallbacks {

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if (activity !is HichyActivity) {
                    activityStack.add(activity)
                }
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (activity !is HichyActivity) {
                    activityStack.remove(activity)
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }
        })
        notify(application)
    }

    private fun notify(context: Context) {
        createNotificationChannelIfNeeded(context)

        val intent = Intent(context, HichyActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(context.packageName)
            .setOngoing(true)
            .setContentIntent(pendingIntent)
            .build()
        val id = System.currentTimeMillis().toInt()
        NotificationManagerCompat.from(context).notify(id, notification)
    }

    private fun createNotificationChannelIfNeeded(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_ID,
                NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            notificationManager?.createNotificationChannel(channel)
        }
    }

    private const val CHANNEL_ID = "hichy"
}