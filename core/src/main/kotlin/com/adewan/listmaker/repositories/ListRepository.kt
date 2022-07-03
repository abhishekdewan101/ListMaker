package com.adewan.listmaker.repositories

import com.adewan.listmaker.db.Collection
import com.adewan.listmaker.models.CollectionType
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ListRepository {
    fun addList(id: UUID, title: String, type: CollectionType)
    fun getAllLists(): Flow<List<Collection>>
    fun getListForId(id: UUID): Collection
}