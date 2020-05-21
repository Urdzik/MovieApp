package com.example.movieapp.ui.home.overview.groupie

//Work with recommended RV
//    private fun recViewingRvViewing() {
//        binding.piker.adapter =
//            RecViewingMovieAdapter(RecViewingMovieAdapter.ClickListener {
//                viewModel.displayPropertyDetails(it)
//            })
//
//        //Add animation for RV
//        binding.piker.setItemTransformer(
//            ScaleTransformer.Builder()
//                .setMaxScale(1.05f)
//                .setMinScale(0.8f)
//                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
//                .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
//                .build()
//        )
//
//        recViewingMovieAdapter = binding.piker.adapter as RecViewingMovieAdapter
//
//        viewModel.recViewingPlayList.observe(viewLifecycleOwner, Observer {
//            recViewingMovieAdapter.submitList(it)
//        })
//    }
//

//                networkSource.fetchSmallMovieList("upcoming", "26f381d6ab8dd659b22d983cab9aa255", "ru"),