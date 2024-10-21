package com.turing.alan.cpifp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.InMemoryTaskRepository
import com.turing.alan.cpifp.data.TaskRepository
import com.turing.alan.cpifp.databinding.FragmentTaskDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TaskDetailFragment: Fragment() {
    private val viewModel:TaskDetailViewModel by viewModels()
    private val args:TaskDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentTaskDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskDetailBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    uiState ->
                    when(uiState) {
                        TaskDetailUiState.Loading -> {}
                        is TaskDetailUiState.Success -> {
                            binding.taskBody.text = uiState.task.body
                        }
                    }
                }
            }
        }

        // val taskId = args.taskId
    }
}