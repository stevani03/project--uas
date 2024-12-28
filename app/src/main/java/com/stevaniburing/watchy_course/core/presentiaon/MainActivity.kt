package com.stevaniburing.watchy_course.core.presentiaon

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.stevaniburing.watchy_course.R
import com.stevaniburing.watchy_course.auth.presentation.login.LoginScreen
import com.stevaniburing.watchy_course.auth.presentation.register.RegisterScreen
import com.stevaniburing.watchy_course.categories.presentation.CoreCategoriesScreen
import com.stevaniburing.watchy_course.details.presentation.CoreDetailsScreen
import com.stevaniburing.watchy_course.favorites.presentation.CoreFavoritesScreen
import com.stevaniburing.watchy_course.main.presentation.MainScreen
import com.stevaniburing.watchy_course.main.presentation.MainUiEvents
import com.stevaniburing.watchy_course.main.presentation.MainViewModel
import com.stevaniburing.watchy_course.main.presentation.main_media_list.MainMediaListScreen
import com.stevaniburing.watchy_course.profile.presentaion.ProfileScreen
import com.stevaniburing.watchy_course.search.peresentation.SearchListScreen
import com.stevaniburing.watchy_course.ui.theme.WatchyCourseTheme
import com.stevaniburing.watchy_course.util.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val coreViewModel by viewModels<CoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WatchyCourseTheme {
                BarColor()

                installSplashScreen().apply {
                    setKeepOnScreenCondition {
                        coreViewModel.isLoading.value
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNav()
                }
            }
        }
    }

    @Composable
    private fun MainNav() {
        val mainViewModel = hiltViewModel<MainViewModel>()
        val mainState = mainViewModel.mainState.collectAsState().value

        val mainNavController = rememberNavController()

        NavHost(
            navController = mainNavController,
            startDestination = Screen.Core.route
        ) {

            composable(route = Screen.Core.route) {
                CoreScreen(
                    coreViewModel.authResultChannel,
                    onAuthorized = {
                        mainViewModel.onEvent(
                            MainUiEvents.LoadAll
                        )
                        mainNavController.popBackStack()
                        mainNavController.navigate(
                            Screen.Main.route
                        )
                    },
                    onNotAuthorized = {
                        mainNavController.popBackStack()
                        mainNavController.navigate(
                            Screen.Login.route
                        )
                    }
                )
            }

            composable(route = Screen.Login.route) {
                LoginScreen(
                    onAuthorized = {
                        mainViewModel.onEvent(
                            MainUiEvents.LoadAll
                        )
                        mainNavController.popBackStack()
                        mainNavController.navigate(
                            Screen.Main.route
                        )
                    },
                    onRegisterClick = {
                        mainNavController.popBackStack()
                        mainNavController.navigate(
                            Screen.Register.route
                        )
                    }
                )
            }

            composable(route = Screen.Register.route) {
                RegisterScreen(
                    onAuthorized = {
                        mainViewModel.onEvent(
                            MainUiEvents.LoadAll
                        )
                        mainNavController.popBackStack()
                        mainNavController.navigate(
                            Screen.Main.route
                        )
                    },
                    onLoginClick = {
                        mainNavController.popBackStack()
                        mainNavController.navigate(
                            Screen.Login.route
                        )
                    }
                )
            }

            composable(route = Screen.Main.route) {
                MainScreen(
                    mainNavController = mainNavController,
                    mainState = mainState,
                    onEvent = mainViewModel::onEvent
                )
            }

            composable(route = Screen.Trending.route) {
                MainMediaListScreen(
                    route = Screen.Trending.route,
                    mainNavController = mainNavController,
                    mainState = mainState,
                    onEvent = mainViewModel::onEvent
                )
            }

            composable(route = Screen.Tv.route) {
                MainMediaListScreen(
                    route = Screen.Tv.route,
                    mainNavController = mainNavController,
                    mainState = mainState,
                    onEvent = mainViewModel::onEvent
                )
            }

            composable(route = Screen.Movies.route) {
                MainMediaListScreen(
                    route = Screen.Movies.route,
                    mainNavController = mainNavController,
                    mainState = mainState,
                    onEvent = mainViewModel::onEvent
                )
            }

            composable(
                route = "${Screen.CoreDetails.route}?mediaId={mediaId}",
                arguments = listOf(
                    navArgument("mediaId") {
                        type = NavType.IntType
                    }
                )
            ) {

                val mediaId = it.arguments?.getInt(
                    "mediaId"
                ) ?: 0

                CoreDetailsScreen(
                    mediaId = mediaId,
                    mainNavController = mainNavController
                )
            }

            composable(Screen.Search.route) {
                SearchListScreen(mainNavController)
            }

            composable(Screen.CoreFavorites.route) {
                CoreFavoritesScreen(mainNavController)
            }

            composable(Screen.CoreCategories.route) {
                CoreCategoriesScreen(mainNavController)
            }

            composable(route = Screen.Profile.route) {
                ProfileScreen {

                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.logged_out),
                        Toast.LENGTH_SHORT
                    ).show()

                    mainNavController.popBackStack()
                    mainNavController.popBackStack()
                    mainNavController.navigate(
                        Screen.Login.route
                    )
                }
            }

        }
    }

    @Composable
    private fun BarColor() {
        val systemUiController = rememberSystemUiController()
        val color = MaterialTheme.colorScheme.background
        LaunchedEffect(color) {
            systemUiController.setSystemBarsColor(color)
        }
    }
}






















