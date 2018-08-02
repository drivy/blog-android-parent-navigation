package net.drivy.parentnavigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.TaskStackBuilder
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import net.drivy.parentnavigation.data.AlbumRepository

class TrackActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context, albumId: Long, trackPosition: Int): Intent = Intent(context, TrackActivity::class.java).apply {
            putExtra(EXTRA_ALBUM_ID, albumId)
            putExtra(EXTRA_TRACK_POSITION, trackPosition)
        }

        private const val EXTRA_ALBUM_ID = "AlbumActivity.ALBUM_ID"
        private const val EXTRA_TRACK_POSITION = "AlbumActivity.TRACK_POSITION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val albumId = intent.getLongExtra(TrackActivity.EXTRA_ALBUM_ID, -1L)
        val trackPosition = intent.getIntExtra(TrackActivity.EXTRA_TRACK_POSITION, -1)

        val album = AlbumRepository().getAlbum(albumId)
        val track = album.tracks[trackPosition - 1]

        findViewById<TextView>(android.R.id.title).text = track.title
        findViewById<TextView>(android.R.id.text1).text = album.title
    }

    override fun onPrepareSupportNavigateUpTaskStack(builder: TaskStackBuilder) {
        super.onPrepareSupportNavigateUpTaskStack(builder)
        val albumId = intent.getLongExtra(TrackActivity.EXTRA_ALBUM_ID, -1L)
        val albumIntent = AlbumActivity.create(this, albumId)
        builder.editIntentAt(builder.intentCount - 1)?.putExtras(albumIntent)
    }
    
}
