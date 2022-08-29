package com.example.githubusersbyazim.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubusersbyazim.model.users.Users
import com.example.githubusersbyazim.model.users.UsersItemModel
import com.example.githubusersbyazim.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.internal.EMPTY_RESPONSE
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {

    val dispatcher = StandardTestDispatcher()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository
    private lateinit var viewModel: UsersViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
        viewModel = UsersViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getDefaultUsersFromAPI_returnsSuccess() = runBlocking {
        whenever(repository.getDefaultUsersFromAPI()).thenReturn(Response.success(Users()))

        viewModel.getDefaultUsers()

        assertEquals(Response.success(Users()), repository.getDefaultUsersFromAPI())
    }

    @Test
    fun getDefaultUsersFromAPI_returnsError() = runBlocking {
        whenever(repository.getDefaultUsersFromAPI()).thenReturn(Response.error(404, EMPTY_RESPONSE))

        viewModel.getDefaultUsers()

//        assertEquals(Response.error(404, EMPTY_RESPONSE), repository.getDefaultUsersFromAPI())
    }

    @Test
    fun getDefaultUsersFromAPI_returnsData() = runBlocking {
        whenever(repository.getDefaultUsersFromAPI()).thenReturn(Response.success(getUsers()))

        viewModel.getDefaultUsers()

        assertEquals(getUsers(), viewModel.usersApiData)
    }

    private fun getUsers(): Users {
        val usersItemModel = UsersItemModel(
            id = 4,
            avatarUrl = "avatarUrl",
            eventsUrl = "eventsUrl"
        )
        val users = Users()
        users.add(usersItemModel)
        users.add(usersItemModel)
        return users
    }
}