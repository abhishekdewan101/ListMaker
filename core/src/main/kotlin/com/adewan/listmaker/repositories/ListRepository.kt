package com.adewan.listmaker.repositories

import com.adewan.listmaker.db.AppList
import com.adewan.listmaker.models.ListType
import java.util.UUID
import kotlinx.coroutines.flow.Flow

interface ListRepository {
    fun addList(id: UUID, title: String, type: ListType)
    fun getAllLists(): Flow<List<AppList>>
}