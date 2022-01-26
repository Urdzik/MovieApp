package com.example.movieapp.ui.user.profile.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.navGraphViewModels
import com.example.movieapp.R
import com.example.movieapp.databinding.SettingsFragmentBinding
import com.example.movieapp.ui.user.profile.ProfileViewModel
import com.example.movieapp.utils.SHARED_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {


    private val viewModel: ProfileViewModel by hiltNavGraphViewModels(R.id.user)
    private lateinit var binding: SettingsFragmentBinding

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = SettingsFragmentBinding.inflate(inflater)
        sharedPreferences = activity?.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE)!!
        editor = sharedPreferences.edit()
        binding.btnSingOut.setOnClickListener {
            viewModel.authUser.value?.signOut()
            viewModel.testTrue()

            editor.putString(SHARED_KEY, "null")
            editor.commit();
        }
        return binding.root
    }
}
