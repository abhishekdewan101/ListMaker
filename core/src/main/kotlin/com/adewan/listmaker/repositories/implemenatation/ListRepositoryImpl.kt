package com.adewan.listmaker.repositories.implemenatation

import com.adewan.listmaker.db.AppList
import com.adewan.listmaker.db.ListMakerDB
import com.adewan.listmaker.models.ListType
import com.adewan.listmaker.repositories.ListRepository
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ListRepositoryImpl @Inject constructor(private val database: ListMakerDB) : ListRepository {
    override fun addList(id: UUID, title: String, type: ListType) {
        database.appListQueries.addList(id = id.toString(), title = title, type = type)
    }

    override fun getAllLists(): Flow<List<AppList>> {
        return database.appListQueries.getAllLists().asFlow().mapToList()
    }

    override fun getListForId(id: UUID): AppList {
        return database.appListQueries.getListForId(id = id.toString()).executeAsOne()
    }
}