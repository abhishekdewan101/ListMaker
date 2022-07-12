package com.adewan.listmaker.home

import androidx.annotation.DrawableRes
import com.adewan.listmaker.ui.home.R

sealed class HomeTabs(val route: String, @DrawableRes val iconId: Int, val name: String) {
    object Games : HomeTabs("games", R.drawable.games, "Games")
    object Movies : HomeTabs("movies", R.drawable.movies, "Movies")
    object Notes : HomeTabs("notes", R.drawable.notes, "Notes")
    object Profile : HomeTabs("profile", R.drawable.profile, "Profile")
}

val allTabs = listOf(HomeTabs.Games, HomeTabs.Movies, HomeTabs.Notes, HomeTabs.Profile)
