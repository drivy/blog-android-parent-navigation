package net.drivy.parentnavigation.data

import java.util.*


class AlbumRepository {
    private val albums = listOf(
            Album(0, "The Raven That Refused to Sing",
                    listOf(Track(1, "Luminol"),
                            Track(2, "Drive Home"),
                            Track(3, "The Holy Drinker"),
                            Track(4, "The Pin Drop"),
                            Track(5, "The Watchmaker"),
                            Track(6, "The Raven That Refused To Sing"))),
            Album(1, "Hand. Cannot. Erase.",
                    listOf(Track(1, "First Regret"),
                            Track(2, "3 Years Older"),
                            Track(3, "Hand Cannot Erase"),
                            Track(4, "Perfect Life"),
                            Track(5, "Routine"),
                            Track(6, "Home Invasion"),
                            Track(7, "Regret #9"),
                            Track(8, "Transience"),
                            Track(9, "Ancestral"),
                            Track(10, "Happy Returns"),
                            Track(11, "Ascendant Here On..."))),
            Album(2, "To the Bone",
                    listOf(Track(1, "To the Bone"),
                            Track(2, "Nowhere Now"),
                            Track(3, "Pariah"),
                            Track(4, "The Same Asylum as Before"),
                            Track(5, "Refuge"),
                            Track(6, "Permanating"),
                            Track(7, "Blank Tapes"),
                            Track(8, "People Who Eat Darkness"),
                            Track(9, "Song of I"),
                            Track(10, "Detonation"),
                            Track(11, "Song of Unborn"))))

    fun getAlbums() = albums
    fun getAlbum(id: Long): Album = albums.first { album -> album.id == id }

    fun getRandomTrack(): Pair<Album, Track> {

        val album = albums[Random().nextInt(albums.size - 1)]
        val track = album.tracks[Random().nextInt(album.tracks.size - 1)]

        return Pair(album, track)
    }
}