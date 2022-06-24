package wj.genericrabbit.app.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

	private val binding by viewBinding(FragmentProfileBinding::bind)
	private val viewModel by viewModels<ProfileViewModel>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
	}
}