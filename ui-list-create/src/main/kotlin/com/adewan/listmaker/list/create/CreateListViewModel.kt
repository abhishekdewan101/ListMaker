package com.adewan.listmaker.list.create

import androidx.lifecycle.ViewModel
import com.adewan.listmaker.models.CollectionType
import com.adewan.listmaker.repositories.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CreateListViewModel @Inject constructor(private val listRepository: ListRepository) :
    ViewModel() {
    fun createList(title: String, type: CollectionType) {
        listRepository.addList(id = UUID.randomUUID(), title = title, type = type)
    }
}