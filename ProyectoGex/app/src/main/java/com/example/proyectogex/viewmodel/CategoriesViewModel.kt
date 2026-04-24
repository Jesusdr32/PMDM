package com.example.proyectogex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectogex.data.repository.CategoryRepository
import com.example.proyectogex.states.CategoryState
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    private val state = CategoryState(CategoryRepository())

    val isLoading get() = state.isLoading

    val errorMessage get() = state.errorMessage

    val categories get() = state.categories

    fun loadCategories() {
        viewModelScope.launch {
            state.loadCategories()
        }
    }
}