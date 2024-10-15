package com.turing.alan.cpifp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turing.alan.cpifp.data.DefaultTaskRepository
import com.turing.alan.cpifp.data.Task
import com.turing.alan.cpifp.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor (
    private val repository: TaskRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<TaskListUiState>(TaskListUiState.Loading)
    val uiState: StateFlow<TaskListUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getStream().collect {
                tasks -> if (tasks.isEmpty())
                    _uiState.value = TaskListUiState.Loading
                else
                _uiState.value = TaskListUiState.Success(tasks)
            }
        }
    }

}

sealed class TaskListUiState {
    data object Loading:TaskListUiState()
    class Success(val tasks:List<Task>):TaskListUiState()
    class Error(val message:String):TaskListUiState()
}