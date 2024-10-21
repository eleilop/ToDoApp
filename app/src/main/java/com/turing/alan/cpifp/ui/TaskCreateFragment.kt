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
import androidx.navigation.fragment.findNavController
import com.turing.alan.cpifp.databinding.FragmentTaskCreateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class TaskCreateFragment : Fragment() {
    private lateinit var binding: FragmentTaskCreateBinding
    private val viewModel:TaskCreateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskCreateBinding.inflate(
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
                        is TaskCreateUiState.Success -> {}
                    }
                }
            }
        }

        binding.saveTaskButton.setOnClickListener {
            val title = binding.titleInput.text.toString()
            val body = binding.bodyInput.text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    viewModel.create(title, body)
                }
            }
            findNavController().popBackStack()
        }
    }
}