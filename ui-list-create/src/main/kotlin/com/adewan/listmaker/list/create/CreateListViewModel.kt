package com.adewan.listmaker.list.create

import androidx.lifecycle.ViewModel
import com.adewan.listmaker.database.CoreList
import com.adewan.listmaker.database.CoreListType
import com.adewan.listmaker.repositories.CoreListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@HiltViewModel
class CreateListViewModel
@Inject
constructor(
    private val coreListRepository: CoreListRepository,
    @Named("io") private val io: CoroutineScope
) : ViewModel() {
    fun createList(title: String, type: CoreListType) {
        io.launch {
            coreListRepository.addList(
                CoreList(
                    id = UUID.randomUUID(),
                    title = title,
                    type = type,
                    createdOn = System.currentTimeMillis()
                )
            )
        }
    }
}
