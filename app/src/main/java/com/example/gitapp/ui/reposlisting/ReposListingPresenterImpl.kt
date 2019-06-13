package com.example.gitapp.ui.reposlisting

import com.example.gitapp.services.GitServices
import com.example.gitapp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ReposListingPresenterImpl(
    private val reposListingView: ReposListingView,
    private val schedulerProvider: SchedulerProvider
) : ReposListingPresenter {

    private var disposable = CompositeDisposable()

    override fun getReposList() {

        reposListingView.showLoader()

        initDisposable()

        disposable.add(
            GitServices.getRepositories()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    reposListingView.showReposList(it)
                    reposListingView.hideLoader()
                }, {
                    reposListingView.showError()
                    reposListingView.hideLoader()
                })
        )
    }

    override fun unsubscribe() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

    fun initDisposable() {
        if (disposable.isDisposed) {
            disposable = CompositeDisposable()
        }
    }
}