package com.example.gitapp.ui.login

import com.example.gitapp.util.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class LoginFragmentPresenterImpl(
    private val scheduler: BaseSchedulerProvider,
    private val interactor: LoginFragmentInteractor,
    private val view: LoginFragmentView
) :
    LoginFragmentPresenter {

    private val disposable = CompositeDisposable()

    override fun startLogin(username: String, password: String) {

        if (interactor.areUserNameAndPasswordCompleted(username, password)) {

            view.showLoader()

            disposable.add(
                interactor.startLogin(username, password)
                    .subscribeOn(scheduler.io())
                    .observeOn(scheduler.ui())
                    .subscribe({
                        view.goToNextScreen()
                        view.hideLoader()
                    }, {
                        view.showError()
                        view.hideLoader()
                    })
            )

        } else {
            view.showError()
        }

    }

    override fun unsubscribe() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}