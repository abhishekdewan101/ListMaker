package com.adewan.listmaker.ui.common

import com.adewan.listmaker.models.CollectionType
import java.util.Locale


fun capitalize(it: CollectionType) = it.name.lowercase(Locale.ROOT).replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.ROOT
    ) else it.toString()
}