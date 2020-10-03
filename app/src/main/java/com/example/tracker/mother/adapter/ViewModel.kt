package com.example.tracker.mother.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tracker.mother.model.Search

class ViewModel(
    private val repository: SearchRepository,
    private val onImagesLoaded: (List<Search>) -> Unit
) {

    fun loadSearch(
        query: String
    ) {
        repository.loadSearch("Antihistamines") {
            it?.alergies1?.let { alergies1 ->
                onImagesLoaded(alergies1)
            }
        }
    }
}