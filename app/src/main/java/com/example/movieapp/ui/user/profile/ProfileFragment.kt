package com.example.movieapp.ui.user.profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.movieapp.R
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.ProfileFragmentBinding
import com.example.movieapp.ui.home.overview.OverviewFragmentDirections
import com.example.movieapp.utils.LOGIN_TAG
import com.example.movieapp.utils.RC_SIGN_IN
import com.example.movieapp.utils.SHARED_KEY
import com.example.movieapp.utils.adapters.PopularMovieAdapter
import com.example.movieapp.utils.adapters.SaveInUserAdapter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ProfileViewModel by navGraphViewModels(R.id.user) { viewModelFactory }
    private lateinit var binding: ProfileFragmentBinding

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var saveInUserAdapter: SaveInUserAdapter

    private var errorSnackbar: Snackbar? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        binding = ProfileFragmentBinding.inflate(inflater)

        sharedPreferences = activity?.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE)!!


        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(binding.root.context, gso)
        // Initialize Firebase Auth

        auth = FirebaseAuth.getInstance()

        binding.settingsButton.setOnClickListener { findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSettingsFragment()) }

        binding.googleLoginBtn.setOnClickListener{ signIn() }
        //Navigate to Detail Activity
        viewModel.navigateToSelectSaveProperty.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToDetailFragment2(it.id))
                viewModel.displayPropertyDetailsCompleted()
            }
        })

        binding.recyclerSave.adapter = SaveInUserAdapter(SaveInUserAdapter.ClickListener {
            viewModel.displayPropertyDetails(it)
        })

        saveInUserAdapter = binding.recyclerSave.adapter as SaveInUserAdapter

        viewModel.movieOfSave.observe(viewLifecycleOwner, Observer {
            saveInUserAdapter.submitList(it)

        })

        //Looking for the internet connection
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer {
            if (it) onNetworkError()
        })


        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        viewModel.test.observe(viewLifecycleOwner, Observer {
            if (it) {
                updateUI(null)

            } else {
                val currentUser = auth.currentUser
                updateUI(currentUser)

            }
        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(LOGIN_TAG, "Google sign in failed", e)
                updateUI(null)
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(LOGIN_TAG, "firebaseAuthWithGoogle:" + acct.id!!)


        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(LOGIN_TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(LOGIN_TAG, "signInWithCredential:failure", task.exception)
                    Snackbar.make(binding.root, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    updateUI(null)
                }


            }
    }

    //Function will show a toast when there is no internet
    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            errorSnackbar = Snackbar.make(binding.root, "Ошибка сети", Snackbar.LENGTH_INDEFINITE)
            errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
            errorSnackbar?.show()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
        viewModel.testFalse()
    }


//    private fun signOut() {
//        // Firebase sign out
//        auth.signOut()
//
//        // Google sign out
//        googleSignInClient.signOut().addOnCompleteListener() {
//            updateUI(null)
//        }
//    }

//    private fun revokeAccess() {
//        // Firebase sign out
//        auth.signOut()
//
//        // Google revoke access
//        googleSignInClient.revokeAccess().addOnCompleteListener() {
//            updateUI(null)
//        }
//    }


    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {

            viewModel.getUser(user)
            viewModel.getAuthUser(auth)

            editor = sharedPreferences.edit()
            editor.putString(SHARED_KEY, user.uid)
            editor.commit()

            binding.userFragment.visibility = View.VISIBLE
            binding.googleLoginBtn.visibility = View.GONE
            binding.saveText.visibility = View.VISIBLE
            binding.recyclerSave.visibility = View.VISIBLE
            binding.prograssBar.visibility = View.VISIBLE

            viewModel.fetchMovieOfSave()
        } else {

            binding.apply {
                googleLoginBtn.visibility = View.VISIBLE
                userFragment.visibility = View.GONE
                saveText.visibility = View.GONE
                recyclerSave.visibility = View.GONE
                prograssBar.visibility = View.GONE
            }

        }
    }




}