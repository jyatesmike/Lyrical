package com.mikeyates.lyrical.ui.search

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.mikeyates.lyrical.LyricalApplication
import com.mikeyates.lyrical.R
import com.mikeyates.lyrical.di.DaggerSearchComponent
import com.mikeyates.lyrical.di.SearchModule
import com.mikeyates.lyrical.network.iTunesSearchResponse
import com.mikeyates.lyrical.presenters.SearchPresenter
import com.mikeyates.lyrical.ui.SongSelectedListener
import com.mikeyates.lyrical.views.SearchView
import javax.inject.Inject

//import com.mikeyates.lyrical.DaggerSearchComponent;

class SearchFragment : Fragment(), SearchView {

    @Inject
    lateinit var presenter: SearchPresenter

    @BindView(R.id.btnSearch)
    lateinit var _btnSearch: Button

    @BindView(R.id.editText)
    lateinit var _etSearchTerms: EditText

    @BindView(R.id.tracklist)
    lateinit var _view: RecyclerView

    //private OnFragmentInteractionListener mListener;

    private var _songsListAdapter: SongsListAdapter? = null

    private var _songSelectedListener: SongSelectedListener? = null

    private var progressDialog: ProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_search, container, false)
        ButterKnife.bind(this, rootView)

        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        val imgr = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imgr.showSoftInput(_etSearchTerms, 0)
        imgr.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    override fun populateList(response: iTunesSearchResponse) {
        if (_songsListAdapter != null) {
            _songsListAdapter!!.data = response.results
            _songsListAdapter!!.notifyDataSetChanged()
        }
    }

    override fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
            dismissKeyboard(_etSearchTerms)
        }
    }

    override fun showProgressDialog() {
        progressDialog = ProgressDialog.show(activity, "Searching", "...", true)
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun initList() {

        _songsListAdapter = SongsListAdapter(_songSelectedListener, R.layout.song_card_view)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        _view!!.layoutManager = linearLayoutManager
        _view!!.setHasFixedSize(true)
        _view!!.adapter = _songsListAdapter

    }

    @OnClick(R.id.btnSearch)
    fun onButtonPressed() {
        presenter!!.refresh(_etSearchTerms!!.text.toString())
    }

    override fun onDetach() {
        super.onDetach()
        //mListener = null;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        DaggerSearchComponent.builder()
                .applicationComponent(LyricalApplication.get(context).component)
                .searchModule(SearchModule(this))
                .build().inject(this)

    }

    fun dismissKeyboard(editText: EditText?) {
        val imgr = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imgr.hideSoftInputFromWindow(editText!!.windowToken, 0)
    }

    companion object {

        fun newInstance(selectedListener: SongSelectedListener): SearchFragment {
            val fragment = SearchFragment()
            fragment._songSelectedListener = selectedListener
            //fragment.setArguments(args);
            return fragment
        }
    }
}// Required empty public constructor
