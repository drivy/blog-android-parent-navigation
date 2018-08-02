package net.drivy.parentnavigation

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import net.drivy.parentnavigation.data.AlbumRepository

class NotificationActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(NotificationChannel("track", "Track", NotificationManager.IMPORTANCE_HIGH))
        }

        val albumRepository = AlbumRepository()
        val (randomAlbum, randomTrack) = albumRepository.getRandomTrack()

        val trackIntent = TrackActivity.create(this, randomAlbum.id, randomTrack.position)
        val pendingIntent = PendingIntent.getActivity(this, 0, trackIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, "track")
                .setColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setSmallIcon(R.drawable.ic_audiotrack_black_24dp)
                .setContentTitle(randomTrack.title)
                .setContentText(randomAlbum.title)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

        notificationManager.notify(0, notification)
        finish()
    }

}