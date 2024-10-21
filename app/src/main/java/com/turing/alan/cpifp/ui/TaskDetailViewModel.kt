package com.turing.alan.cpifp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turing.alan.cpifp.data.Task
import com.turing.alan.cpifp.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor (
    private val repository: TaskRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<TaskDetailUiState>(TaskDetailUiState.Loading)
    val uiState: StateFlow<TaskDetailUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getStream().collect {
            tasks ->
            if (tasks.isEmpty())
                _uiState.value = TaskDetailUiState.Loading
            }
        }
    }
}

sealed class TaskDetailUiState {
    data object Loading:TaskDetailUiState()
    class Success(val task:Task):TaskDetailUiState()
}