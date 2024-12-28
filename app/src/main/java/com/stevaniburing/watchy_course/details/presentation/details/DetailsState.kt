package com.stevaniburing.watchy_course.details.presentation.details

import com.stevaniburing.watchy_course.main.domain.model.Media

/**
 * @author Ahmed Guedmioui
 */
data class DetailsState(
    val media: Media? = null,
    val videoId: String = "",
    val readableTime: String = "",

    val videoList: List<String> = emptyList(),
    val similarList: List<Media> = emptyList(),

    val showAlertDialog: Boolean = false,
    val alertDialogType: Int = 0
)
























