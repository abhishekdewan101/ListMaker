package com.adewan.listmaker.ui.common

import com.adewan.listmaker.models.ListType
import java.util.Locale


fun capitalize(it: ListType) = it.name.lowercase(Locale.ROOT).replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.ROOT
    ) else it.toString()
}