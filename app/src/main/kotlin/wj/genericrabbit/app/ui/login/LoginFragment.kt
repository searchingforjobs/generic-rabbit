package wj.genericrabbit.app.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

	private val binding by viewBinding(FragmentLoginBinding::bind)
	private val viewModel by viewModels<LoginViewModel>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
	}
}