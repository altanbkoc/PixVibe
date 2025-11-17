package com.altankoc.pixvibe.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altankoc.pixvibe.domain.usecase.GetImagesByCategoryUseCase
import com.altankoc.pixvibe.domain.usecase.SearchImagesUseCase
import com.altankoc.pixvibe.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchImagesUseCase: SearchImagesUseCase,
    private val getImagesByCategoryUseCase: GetImagesByCategoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchScreenState())
    val state: StateFlow<SearchScreenState> = _state.asStateFlow()

    private val searchQueryFlow = MutableStateFlow("")

    init {
        observeSearchQuery()
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            searchQueryFlow
                .debounce(500)
                .filter { it.length >= 2 }
                .distinctUntilChanged()
                .collect { query ->
                    performSearch(query)
                }
        }
    }

    fun onSearchQueryChange(query: String) {
        _state.update { it.copy(searchQuery = query) }
        searchQueryFlow.value = query

        if (query.isEmpty()) {
            _state.update {
                it.copy(
                    searchResults = emptyList(),
                    isSearchActive = false,
                    error = null
                )
            }
        }
    }

    fun onCategorySelected(category: String) {
        _state.update {
            it.copy(
                selectedCategory = if (it.selectedCategory == category) null else category,
                searchQuery = ""
            )
        }

        val selectedCat = _state.value.selectedCategory
        if (selectedCat != null) {
            searchByCategory(selectedCat)
        } else {
            _state.update { it.copy(searchResults = emptyList(), isSearchActive = false) }
        }
    }

    private fun performSearch(query: String) {
        if (query.isBlank()) return

        viewModelScope.launch {
            searchImagesUseCase(query, page = 1).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                error = null,
                                isSearchActive = true
                            )
                        }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                searchResults = resource.data,
                                isLoading = false,
                                error = null,
                                selectedCategory = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = resource.message
                            )
                        }
                    }
                }
            }
        }
    }

    private fun searchByCategory(category: String) {
        viewModelScope.launch {
            getImagesByCategoryUseCase(category, page = 1).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                error = null,
                                isSearchActive = true
                            )
                        }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                searchResults = resource.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = resource.message
                            )
                        }
                    }
                }
            }
        }
    }

    fun clearSearch() {
        _state.update {
            SearchScreenState()
        }
        searchQueryFlow.value = ""
    }
}