package com.amjad.deezerchallange.presentation.ui.searchArtist


import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Context.SEARCH_SERVICE
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.amjad.deezerchallange.R
import com.amjad.deezerchallange.common.models.Status
import com.amjad.deezerchallange.domain.models.ArtistDomainModel
import com.amjad.deezerchallange.presentation.ui.base.BaseFragment
import com.amjad.deezerchallange.presentation.viewModels.SearchArtistViewModel
import com.amjad.deezerchallange.presentation.viewModels.ViewModelProviderFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_search_artists.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider


class SearchArtistsFragment : BaseFragment(),
    ArtistsPagedAdapter.ArtistAdapterListener {


    private fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun removeLoading() {
        loading.visibility = View.GONE
    }

    private fun showError(message: String?) {
        removeLoading()
        Toast.makeText(activity, message ?: "there was an error", Toast.LENGTH_LONG).show()
    }

    private fun renderData(data: PagedList<ArtistDomainModel>?) {
        removeLoading()
        artistsPagedAdapter.submitList(data)

    }

    override fun getLayoutRes(): Int = R.layout.fragment_search_artists

    override fun attachFragmentInteractionListener(context: Context) {
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var artistsPagedAdapter: ArtistsPagedAdapter

    private lateinit var viewModel: SearchArtistViewModel

    @Inject
    lateinit var linearLayoutManager: Provider<LinearLayoutManager>

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()

        navController = Navigation.findNavController(view)

        viewModel = ViewModelProviders.of(this, viewModelProviderFactory)
            .get(SearchArtistViewModel::class.java)



        viewModel.observeSearchResult()
            .observe(this, Observer {
                when (it.status) {
                    Status.SUCCESS -> renderData(it.data)
                    Status.LOADING -> showLoading()
                    Status.ERROR -> showError(it.message)
                }
            })
    }


    @SuppressLint("CheckResult")
    private fun setupUi() {

        artist_recycler.layoutManager = linearLayoutManager.get()
        artist_recycler.adapter = artistsPagedAdapter

        artistsPagedAdapter.listener = this

    }

    private fun createObservableFromView(searchView: SearchView): Observable<String> {
        val subject = PublishSubject.create<String>()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                subject.onComplete()
                searchView.clearFocus() //if you want to close keyboard
                return false
            }

            override fun onQueryTextChange(text: String): Boolean {
                subject.onNext(text)
                return false
            }
        })

        return subject
    }

    override fun onArtistClick(id: String?) {
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.show()
        hideKeyboard()
        navController.navigate(
            SearchArtistsFragmentDirections.actionSearchArtistsFragmentToAlbumsListFragment(
                id!!
            )
        )
    }


    private fun submitAndObserveSearch(name: String) {
        viewModel.setSearchString(name)
    }

    @SuppressLint("CheckResult")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.search_menu, menu)

        val manager = activity?.getSystemService(SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search_item)?.actionView
        //val searchView = searchItem?.actionView as SearchView
        val searchView = searchItem?.findViewById(R.id.searchView) as SearchView


        searchView.setSearchableInfo(manager.getSearchableInfo(activity?.componentName))
        val si = manager.getSearchableInfo(activity?.componentName)
        val options = searchView.imeOptions
        searchView.imeOptions = options or EditorInfo.IME_FLAG_NO_EXTRACT_UI
        searchView.setSearchableInfo(si)
        //searchView.onActionViewExpanded()
        searchView.maxWidth = Integer.MAX_VALUE

        createObservableFromView(searchView)
            .debounce(200, TimeUnit.MILLISECONDS)
            .filter { text -> text.isNotEmpty() }
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                submitAndObserveSearch(it)
            }
    }
}
