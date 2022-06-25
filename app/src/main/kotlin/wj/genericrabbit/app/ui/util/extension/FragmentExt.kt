package wj.genericrabbit.app.ui.util.extension

import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import wj.genericrabbit.app.R

fun <T> Fragment.observeUiState(uiStateFlow: Flow<T>, render: (T) -> Unit): Job {
	return viewLifecycleOwner.lifecycleScope.launch {
		viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
			uiStateFlow.collect(render)
		}
	}
}

