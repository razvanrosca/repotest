package com.example.gitapp.ui.login

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginFragmentInteractorImplTest {

    private lateinit var interactor: LoginFragmentInteractor

    @Before
    fun setUp() {
        interactor = LoginFragmentInteractorImpl()
    }

    //we can create a class that retrieves random string values for username and password
    //for generic tests

    @Test
    fun `areUserNameAndPasswordCompleted whenUsernameIsEmpty thenReturnFalse`() {
        val userName = ""
        val password = "asda"

        val areFieldsCompleted = interactor.areUserNameAndPasswordCompleted(userName, password)

        assertFalse(areFieldsCompleted)


    }
    @Test
    fun `areUserNameAndPasswordCompleted whenPasswordIsEmpty thenReturnFalse`() {
        val userName = "asda"
        val password = ""

        val areFieldsCompleted = interactor.areUserNameAndPasswordCompleted(userName, password)

        assertFalse(areFieldsCompleted)


    }

    @Test
    fun `areUserNameAndPasswordCompleted whenPasswordAreSet thanReturnTrue`() {
        val userName = "asda"
        val password = "asdsfa"

        val areFieldsSet = interactor.areUserNameAndPasswordCompleted(userName, password)

        assertTrue(areFieldsSet)


    }
}