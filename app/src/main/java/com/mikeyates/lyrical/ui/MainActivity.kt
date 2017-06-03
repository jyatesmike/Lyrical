package com.mikeyates.lyrical.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.mikeyates.lyrical.R
import com.mikeyates.lyrical.models.TrackModel
import com.mikeyates.lyrical.ui.lyrics.LyricsFragment
import com.mikeyates.lyrical.ui.search.SearchFragment
import java.io.IOException

class MainActivity : AppCompatActivity() {

    @BindView(R.id.navigation)
    lateinit var _bottomNavigationView: BottomNavigationView
    protected lateinit var _searchFragment: SearchFragment
    protected lateinit var _lyricsFragment: LyricsFragment
    private val _fragmentManager = supportFragmentManager
    private var _fragment: Fragment? = null
    private var _activeBottomNavigationViewMenuItem: MenuItem? = null
    private var _mediaPlayer: MediaPlayer? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = _fragmentManager.beginTransaction()

        when (item.itemId) {
            R.id.navigation_search -> _fragment = _searchFragment
            R.id.navigation_lyrics -> _fragment = _lyricsFragment
            R.id.navigation_favorites -> {
            }
        }//not implemented yet

        //this will always reload the fragments.  with more time I would use a viewpager or add some logic
        //to not reload if already loaded
        transaction.replace(R.id.container, _fragment).commit()

        if (item !== _activeBottomNavigationViewMenuItem) {
            _activeBottomNavigationViewMenuItem!!.isChecked = false
            _activeBottomNavigationViewMenuItem = item
            _activeBottomNavigationViewMenuItem!!.isChecked = true
        }

        true
    }

    internal var _onSongListener: SongSelectedListener = object : SongSelectedListener {
        override fun onItemSelected(position: Int, trackModel: TrackModel?) {
            val artistName = trackModel?.artistName
            val songName = trackModel?.trackName

            _lyricsFragment = LyricsFragment.newInstance(artistName, songName)

            val menu = _bottomNavigationView!!.menu
            mOnNavigationItemSelectedListener.onNavigationItemSelected(menu.findItem(R.id.navigation_lyrics))
        }

        override fun onPreviewItem(position: Int, trackModel: TrackModel?) {
            val artistName = trackModel!!.artistName
            val songName = trackModel!!.trackName

            _lyricsFragment = LyricsFragment.newInstance(artistName, songName)

            if (_mediaPlayer == null || !_mediaPlayer!!.isPlaying) {

                _mediaPlayer = MediaPlayer()
                try {
                    _mediaPlayer!!.setDataSource(trackModel.previewUrl)
                    _mediaPlayer!!.prepare()
                    _mediaPlayer!!.start()

                } catch (ex: IOException) {
                    //do nothing
                }

            } else {
                _mediaPlayer!!.release()
                _mediaPlayer = null
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        initFragments()

        _activeBottomNavigationViewMenuItem = _bottomNavigationView!!.menu.findItem(R.id.navigation_search)
        _bottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    protected fun initFragments() {
        _searchFragment = SearchFragment.newInstance(_onSongListener)
        _lyricsFragment = LyricsFragment.newInstance("", "")

        _fragmentManager.beginTransaction().add(R.id.container, _searchFragment, "0").commit()
        _fragmentManager.beginTransaction().add(R.id.container, _lyricsFragment, "1").commit()

        _fragment = _searchFragment
    }
}
