package com.stevaniburing.watchy_course.main.presentation

/**
 * @author Ahmed Guedmioui
 */
sealed class MainUiEvents {

    data class Refresh(val route: String): MainUiEvents()
    data class Paginate(val route: String): MainUiEvents()

    object LoadAll: MainUiEvents()

}