package com.altankoc.pixvibe.presentation.screens.search

import com.altankoc.pixvibe.domain.model.Image

data class SearchScreenState(
    val searchQuery: String = "",
    val searchResults: List<Image> = emptyList(),
    val selectedCategory: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSearchActive: Boolean = false
)