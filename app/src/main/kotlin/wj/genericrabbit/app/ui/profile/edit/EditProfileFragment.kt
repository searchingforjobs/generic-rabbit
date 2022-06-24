package wj.genericrabbit.app.ui.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import wj.genericrabbit.app.databinding.BottomSheetEditProfileBinding
import wj.genericrabbit.app.domain.model.User

@AndroidEntryPoint
class EditProfileFragment : BottomSheetDialogFragment() {

	private var binding: BottomSheetEditProfileBinding? = null
	private val viewModel by viewModels<EditProfileViewModel>()
	private var currentUser: User? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = BottomSheetEditProfileBinding.inflate(inflater)
		return binding?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
	}

	override fun onDestroyView() {
		super.onDestroyView()
		binding = null
	}

	private fun EditText.setString(string: String) {
		setText(string)
		text?.let { text ->
			setSelection(text.length)
		}
	}

	private inline fun EditText.updateUser(crossinline transform: (CharSequence?, User) -> User) {
		addTextChangedListener(
			onTextChanged = { text, _, _, _ ->
				currentUser?.let {
					viewModel.updateCurrentUserState(transform(text, it))
				}
			}
		)
	}
}