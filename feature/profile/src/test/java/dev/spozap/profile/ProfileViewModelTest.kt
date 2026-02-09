package dev.spozap.profile

import app.cash.turbine.test
import dev.spozap.core.data.repository.ProductsRepository
import dev.spozap.core.data.repository.UserRepository
import dev.spozap.core.model.User
import dev.spozap.core.testing.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProfileViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var userRepository: UserRepository
    private lateinit var productsRepository: ProductsRepository

    private lateinit var viewModel: ProfileViewModel

    @Before
    fun setup() {
        userRepository = mockk()
        productsRepository = mockk()
    }

    @Test
    fun `initial ProfileUiState is Loading`() = runTest {
        every { userRepository.getProfile() } returns emptyFlow()
        every { productsRepository.getFavoritesCount() } returns flowOf(0)

        viewModel = ProfileViewModel(userRepository, productsRepository)

        viewModel.profileUiState.test {
            assertEquals(ProfileUIState.Loading, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when profile is loaded ui state is Success`() = runTest {
        val fakeUser = User("1", "Username", "test@test.com", "+34655555555")

        every { userRepository.getProfile() } returns flowOf(fakeUser)
        every { productsRepository.getFavoritesCount() } returns flowOf(0)

        viewModel = ProfileViewModel(userRepository, productsRepository)

        viewModel.profileUiState.test {
            assertEquals(ProfileUIState.Success(fakeUser), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when profile is loaded ui state is Error`() = runTest {

        every { userRepository.getProfile() } returns flow {
            throw Exception("boom")
        }
        every { productsRepository.getFavoritesCount() } returns flowOf(0)

        viewModel = ProfileViewModel(userRepository, productsRepository)

        viewModel.profileUiState.test {
            val state = awaitItem()
            assertTrue(state is ProfileUIState.Error)
            cancelAndIgnoreRemainingEvents()
        }
    }


}