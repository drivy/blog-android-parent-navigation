package net.drivy.parentnavigation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import net.drivy.parentnavigation.data.Album
import net.drivy.parentnavigation.data.AlbumRepository


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val albumRepository = AlbumRepository()
        val (randomAlbum, randomTrack) = albumRepository.getRandomTrack()

        with(findViewById<TextView>(R.id.random_track)) {
            text = randomTrack.title
            setOnClickListener { startActivity(TrackActivity.create(this@MainActivity, randomAlbum.id, randomTrack.position)) }
        }

        with(findViewById<ListView>(android.R.id.list)) {
            adapter = AlbumAdapter(this@MainActivity, albumRepository.getAlbums())
            onItemClickListener = AdapterView.OnItemClickListener { _, _, _, id ->
                startActivity(AlbumActivity.create(this@MainActivity, id))
            }
        }
    }

    inner class AlbumAdapter(context: Context?, albums: List<Album>) : ArrayAdapter<Album>(context, R.layout.list_item_album, albums) {
        override fun getItemId(position: Int): Long {
            return getItem(position).id
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val album = getItem(position)
            val view = super.getView(position, convertView, parent) as TextView
            view.text = album.title
            return view
        }
    }

}
