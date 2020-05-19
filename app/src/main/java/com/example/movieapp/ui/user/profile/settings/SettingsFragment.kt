package com.example.movieapp.ui.user.profile.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.example.movieapp.R
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.SettingsFragmentBinding
import com.example.movieapp.ui.user.profile.ProfileViewModel
import javax.inject.Inject

class SettingsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: ProfileViewModel by navGraphViewModels(R.id.user) { viewModelFactory }
    private lateinit var binding: SettingsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)

        binding = SettingsFragmentBinding.inflate(inflater)

        binding.btnSingOut.setOnClickListener {
            viewModel.authUser.value?.signOut()
            viewModel.testTrue()
            println(viewModel.test.value.toString())

        }



        return binding.root
    }
}
