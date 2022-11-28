
package com.petfinder.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.petfinder.ui.animaldetails.DetailsScreen
import com.petfinder.ui.animals.AnimalsScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppScreen() {
    val navController = rememberAnimatedNavController()
    val actions = remember(navController) { NavigationActions(navController) }

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.AnimalsScreen.route,
    ) {
        addAnimalsScreen(actions)
        addAnimalDetailScreen()
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.addAnimalsScreen(
    actions: NavigationActions,
) {
    composable(
        route = Screen.AnimalsScreen.route,
        enterTransition = { initial, _ ->
            when (initial.destination.route) {
                Screen.DetailsScreen.route -> {
                    slideAndFadeIn(from = Direction.Left)
                }
                else -> null
            }
        },
        exitTransition = { _, target ->
            when (target.destination.route) {
                Screen.DetailsScreen.route -> {
                    slideAndFadeOut(to = Direction.Left)
                }
                else -> null
            }
        }
    ) {
        AnimalsScreen(actions)
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.addAnimalDetailScreen() {
    composable(
        route = Screen.DetailsScreen.route,
        arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
            }
        ),
        enterTransition = { initial, _ ->
            when (initial.destination.route) {
                Screen.AnimalsScreen.route -> {
                    slideAndFadeIn(from = Direction.Right)
                }
                else -> null
            }
        },
        exitTransition = { _, target ->
            when (target.destination.route) {
                Screen.AnimalsScreen.route -> {
                    slideAndFadeOut(to = Direction.Right)
                }
                else -> null
            }
        }
    ) {
        DetailsScreen()
    }
}
