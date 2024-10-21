package com.turing.alan.cpifp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turing.alan.cpifp.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskCreateViewModel @Inject constructor (
    private val repository: TaskRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<TaskCreateUiState>(TaskCreateUiState.Success)
    val uiState: StateFlow<TaskCreateUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = TaskCreateUiState.Success
        }
    }

    suspend fun create(title: String, body: String) {
        repository.create(title, body)
    }
}

sealed class TaskCreateUiState {
    data object Success:TaskCreateUiState()
}