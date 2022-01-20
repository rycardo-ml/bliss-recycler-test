package com.example.recyclerview.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.recyclerview.ui.dashboard.movies.MovieDatasourceFactory
import com.example.recyclerview.ui.dashboard.movies.service.Movie
import com.example.recyclerview.ui.dashboard.movies.service.tmdbService
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import java.util.*

class DashboardViewModel : ViewModel() {

    private var rxPageList: Observable<PagedList<Movie>>

    private val compositeDisposable = CompositeDisposable()

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val config = PagedList.Config.Builder()
        .setPageSize(5)
        .setInitialLoadSizeHint(2 * 15)
        .setEnablePlaceholders(true)
        .build()

    private val datasourceFactory = MovieDatasourceFactory(tmdbService, compositeDisposable)


    init {
        rxPageList = RxPagedListBuilder(datasourceFactory, config).buildObservable()
    }

    fun fetchPages(onPageReady: (PagedList<Movie>) -> Unit, onPageLoadFailed: (Throwable) -> Unit) {
        compositeDisposable.add(
            rxPageList
                .subscribeWith(object : DisposableObserver<PagedList<Movie>>() {
                    override fun onComplete() {

                    }

                    override fun onNext(pagedMovieList: PagedList<Movie>) {
                        onPageReady(pagedMovieList)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("melo", "error: ${e}")
                        onPageLoadFailed(e)
                    }
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}