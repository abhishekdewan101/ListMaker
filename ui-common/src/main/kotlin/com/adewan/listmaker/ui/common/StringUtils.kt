package com.adewan.listmaker.ui.common

import com.adewan.listmaker.database.CoreListType
import java.util.Locale


fun capitalize(it: CoreListType) = it.name.lowercase(Locale.ROOT).replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.ROOT
    ) else it.toString()
}