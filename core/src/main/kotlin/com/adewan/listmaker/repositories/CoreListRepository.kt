package com.adewan.listmaker.repositories

import com.adewan.listmaker.database.CoreList
import java.util.UUID
import kotlinx.coroutines.flow.Flow

interface CoreListRepository {
    suspend fun addList(coreList: CoreList)
    suspend fun getAllLists(): Flow<List<CoreList>>
    suspend fun getListForId(id: UUID): CoreList?
}
