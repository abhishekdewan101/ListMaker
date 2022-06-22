package com.adewan.listmaker.repositories.implemenatation

import com.adewan.listmaker.db.AppList
import com.adewan.listmaker.db.ListMakerDB
import com.adewan.listmaker.extensions.asBytes
import com.adewan.listmaker.models.ListType
import com.adewan.listmaker.repositories.ListRepository
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ListRepositoryImpl @Inject constructor(private val database: ListMakerDB) : ListRepository {
    override fun addList(id: UUID, title: String, type: ListType) {
        database.appListQueries.addList(id = id.asBytes(), title = title, type = type)
    }

    override fun getAllLists(): Flow<List<AppList>> {
        return database.appListQueries.getAllLists().asFlow().mapToList()
    }
}