package com.example.gitapp.ui.readme

import com.example.gitapp.services.GitServices
import com.example.gitapp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ReadmeFragmentPresenterImpl(
    private val interactor: ReadmeFragmentInteractor,
    private val view: ReadmeFragmentView,
    private val schedulerProvider: SchedulerProvider
) : ReadmeFragmentPresenter {

    private var disposable = CompositeDisposable()

    override fun getReadme(fullName: String) {
        disposable.add(
            GitServices.getReadme(fullName)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    view.showContent(interactor.getDecodedData(it.content))
                }, {
                    view.showError()
                })
        )
    }

    override fun unsubscribe() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

}