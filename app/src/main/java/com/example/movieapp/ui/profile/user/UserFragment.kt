package com.example.movieapp.ui.profile.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.UserFragmentBinding
import javax.inject.Inject


class UserFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var binding: UserFragmentBinding
    lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)

        binding = UserFragmentBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        val args = UserFragmentArgs.fromBundle(requireArguments())

        viewModel.buttonOfSettings.observe(viewLifecycleOwner, Observer {
            if (it){
                findNavController().navigate(UserFragmentDirections.actionUserFragmentToSettingsFragment(args.thisUser))
                viewModel.finishedToClickOfSettingsButton()
            }

        })





        viewModel.getUser(args.thisUser)
        Log.i("TAG", args.thisUser.photoUrl.toString())

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


}
