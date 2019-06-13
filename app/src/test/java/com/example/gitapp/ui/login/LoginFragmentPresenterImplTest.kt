package com.example.gitapp.ui.login

import com.example.gitapp.model.UserDetails
import com.example.gitapp.services.GitServices
import com.example.gitapp.util.BaseSchedulerProvider
import com.example.gitapp.util.ImmediateSchedulerProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mockito.*
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner


@RunWith(PowerMockRunner::class)
@PrepareForTest(GitServices::class)
class LoginFragmentPresenterImplTest {

    private val interactor = mock(LoginFragmentInteractor::class.java)
    private val view = mock(LoginFragmentView::class.java)
    private lateinit var presenter: LoginFragmentPresenter
    private lateinit var schedulerProvider: BaseSchedulerProvider


    @Before
    fun setUp() {

        PowerMockito.mockStatic(GitServices::class.java)


        schedulerProvider = ImmediateSchedulerProvider()
        presenter = LoginFragmentPresenterImpl(schedulerProvider, interactor, view)
    }

    @Test
    fun `startLogin_whenUserAndPasswordNotCompleted_thanDisplayError`() {

        val username = ""
        val password = ""

        `when`(interactor.areUserNameAndPasswordCompleted(username, password)).thenReturn(false)

        presenter.startLogin(username, password)

        verify(view).showError()

    }

    @Test
    fun startLogin_whenUserAndPasswordCompletedAndRequestFinishesWithSuccess_thanSetViews() {

        val username = Matchers.any(String::class.java)
        val password = Matchers.any(String::class.java)
        val userDetails = UserDetails()

        `when`(interactor.areUserNameAndPasswordCompleted(username, password)).thenReturn(true)
        `when`(interactor.startLogin(username, password)).thenReturn(Single.just(userDetails))

        presenter.startLogin(username, password)

        verify(view).showLoader()
        verify(view).goToNextScreen()
        verify(view).hideLoader()


    }

    @Test
    fun startLogin_whenUserAndPasswordCompletedAndRequestFinishesWithError_thanSetViews() {

        val username = ""
        val password = ""

        `when`(interactor.areUserNameAndPasswordCompleted(username, password)).thenReturn(true)
        `when`(interactor.startLogin(username, password)).thenReturn(Single.error(Throwable()))

        presenter.startLogin(username, password)

        verify(view).showLoader()
        verify(view).showError()
        verify(view).hideLoader()

    }

}