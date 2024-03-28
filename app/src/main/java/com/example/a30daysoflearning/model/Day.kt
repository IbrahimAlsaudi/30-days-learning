package com.example.a30daysoflearning.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    @DrawableRes val imageRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    val dayNumber: Int
)
