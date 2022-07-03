package com.adewan.listmaker.repositories.implemenatation

import com.adewan.listmaker.db.Collection
import com.adewan.listmaker.db.ListMakerDB
import com.adewan.listmaker.models.CollectionType
import com.adewan.listmaker.repositories.ListRepository
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(private val database: ListMakerDB) : ListRepository {
    override fun addList(id: UUID, title: String, type: CollectionType) {
        database.collectionQueries.insert(
            id = id.toString(),
            title = title,
            type = type,
            createdAt = System.currentTimeMillis()
        )
    }

    override fun getAllLists(): Flow<List<Collection>> {
        return database.collectionQueries.getAll().asFlow().mapToList()
    }

    override fun getListForId(id: UUID): Collection {
        return database.collectionQueries.getCollectionForId(id = id.toString()).executeAsOne()
    }
}