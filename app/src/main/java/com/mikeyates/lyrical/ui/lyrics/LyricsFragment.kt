package com.mikeyates.lyrical.ui.lyrics

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.mikeyates.lyrical.LyricalApplication
import com.mikeyates.lyrical.R
import com.mikeyates.lyrical.di.DaggerLyricsComponent
import com.mikeyates.lyrical.di.LyricsModule
import com.mikeyates.lyrical.models.LyricsModel
import com.mikeyates.lyrical.presenters.LyricsPresenter
import com.mikeyates.lyrical.views.LyricsView
import javax.inject.Inject

/**
 * Created by jamesyates on 5/29/17.
 */

class LyricsFragment : Fragment(), LyricsView {
    @Inject
    lateinit var presenter: LyricsPresenter

    private var progressDialog: ProgressDialog? = null

    private var mArtist: String? = null
    private var mSong: String? = null

    @BindView(R.id.webview_lyrics)
    lateinit var wvLyrics: WebView

    @BindView(R.id.lyrics)
    lateinit var tvLyrics: TextView

    @BindView(R.id.song_name)
    lateinit var tvSongName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mArtist = arguments.getString(ARG_ARTIST)
            mSong = arguments.getString(ARG_SONG)
        }

        presenter!!.refresh(mArtist, mSong)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_lyrics, container, false)
        ButterKnife.bind(this, rootView)

        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        DaggerLyricsComponent.builder()
                .applicationComponent(LyricalApplication.get(context).component)
                .lyricsModule(LyricsModule(this))
                .build().inject(this)
    }

    override fun onDetach() {
        super.onDetach()
    }


    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun refreshScreen(resp: LyricsModel?) {
        if (resp != null) {
            tvSongName!!.text = resp.song
            tvLyrics!!.text = resp.lyrics
            wvLyrics!!.settings.javaScriptEnabled = true
            wvLyrics!!.setWebViewClient(object : WebViewClient() {
                override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                    Toast.makeText(activity, description, Toast.LENGTH_SHORT).show()
                }
            })
            wvLyrics!!.loadUrl(resp.url)
        }
    }

    override fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    override fun showProgressDialog() {
        progressDialog = ProgressDialog.show(activity, "Searching", "...", true)
    }

    companion object {

        private val ARG_ARTIST = "artist"
        private val ARG_SONG = "song"

        fun newInstance(artist: String?, song: String?): LyricsFragment {
            val fragment = LyricsFragment()
            val args = Bundle()
            args.putString(ARG_ARTIST, artist)
            args.putString(ARG_SONG, song)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
