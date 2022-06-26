package wj.genericrabbit.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import wj.genericrabbit.app.R
import wj.genericrabbit.app.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

	private val binding by viewBinding(ActivityMainBinding::bind, R.id.container)
	private lateinit var appBarConfiguration: AppBarConfiguration

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)
		val navController = binding.navHostFragmentActivityMain.getFragment<NavHostFragment>().navController
		appBarConfiguration = AppBarConfiguration(navController.graph)
		navController.addOnDestinationChangedListener { _, destination, _ ->
			binding.navView.isVisible = isNavigationVisible(destination.id)
			binding.toolbar.isVisible = isNavigationVisible(destination.id)
		}
		setupActionBarWithNavController(navController, appBarConfiguration)
		binding.navView.setupWithNavController(navController)
	}

	override fun onSupportNavigateUp(): Boolean {
		val navController = findNavController(R.id.nav_host_fragment_activity_main)
		return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
	}


	private fun isNavigationVisible(id: Int) = id != R.id.navigation_identification_face ||
			id != R.id.navigation_identification_qr
}