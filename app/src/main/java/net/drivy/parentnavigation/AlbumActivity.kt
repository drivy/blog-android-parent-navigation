package net.drivy.parentnavigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import net.drivy.parentnavigation.data.AlbumRepository
import net.drivy.parentnavigation.data.Track

class AlbumActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context, id: Long): Intent = Intent(context, AlbumActivity::class.java).apply {
            putExtra(EXTRA_ALBUM_ID, id)
        }

        private const val EXTRA_ALBUM_ID = "AlbumActivity.ALBUM_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val albumId = intent.getLongExtra(EXTRA_ALBUM_ID, -1L)
        val album = AlbumRepository().getAlbum(albumId)

        findViewById<TextView>(android.R.id.title).text = album.title

        with(findViewById<ListView>(android.R.id.list)) {
            adapter = TrackAdapter(this@AlbumActivity, album.tracks)
            onItemClickListener = AdapterView.OnItemClickListener { adapter, _, position, _ ->
                val track = adapter.getItemAtPosition(position) as Track
                startActivity(TrackActivity.create(this@AlbumActivity, album.id, track.position))
            }
        }
    }

    inner class TrackAdapter(context: Context?, tracks: List<Track>) : ArrayAdapter<Track>(context, R.layout.list_item_track, tracks) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val track = getItem(position)
            val view = super.getView(position, convertView, parent) as TextView
            view.text = track.title
            return view
        }
    }
    
}
