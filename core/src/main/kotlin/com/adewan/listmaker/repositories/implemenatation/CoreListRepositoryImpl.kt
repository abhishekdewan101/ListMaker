package com.adewan.listmaker.repositories.implemenatation

import com.adewan.listmaker.database.CoreList
import com.adewan.listmaker.database.Database
import com.adewan.listmaker.repositories.CoreListRepository
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class CoreListRepositoryImpl @Inject constructor(private val database: Database) :
    CoreListRepository {
    override suspend fun addList(coreList: CoreList) {
        database.coreListDao().insert(list = coreList)
    }

    override suspend fun getAllLists(): Flow<List<CoreList>> {
        return database.coreListDao().getAll()
    }

    override suspend fun getListForId(id: UUID): CoreList? {
        return database.coreListDao().getCoreListForId(id = id)
    }
}
