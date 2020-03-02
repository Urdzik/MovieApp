
![MovieApp](https://github.com/Urdzik/MovieApp/blob/master/readme/Photo%20for%20github.png?raw=true)
<br/><br/>
[![Kotlin](https://img.shields.io/badge/Kotlin-1.3.61-blue.svg)](https://kotlinlang.org)
[![Dagger2](https://img.shields.io/badge/Dagger%202-2.26-red.svg)](https://github.com/google/dagger)
[![Material design](https://img.shields.io/badge/Material%20Design-1.2.0--alpha%205-%237464f2.svg)](https://material.io)
[![The Movie DB](https://img.shields.io/badge/TMDB-V4-%2300d573.svg)](https://www.themoviedb.org/)

### Application which retrieves data from Webserver (via Retrofit), saves it into cache and get from it if user is offline. There are applying MVVM architecture pattern and Dagger 2 example.
 # Overview:

* ### __Model__
   Model is implemented as Repository pattern. Firstly it begins from internet connection checking. Consequently if it's alive we're retrieving data from the server (by using Retrofit 2). Otherwise we're trying to fetch data from the cache itself.
* ### __View__
     View is realised as 2 fragments. First one contains RecyclerView, second one depends on clicks on recycler-items and finally displays detailed data fetched from the Model. It implements state saving reflected on configuration changes.
    <br/><br/>

    <div align = "center">
     <img src = "https://github.com/Urdzik/MovieApp/blob/master/readme/Screenshot_1582798408_framed.png?raw=true" width="330">
     <img src = "https://github.com/Urdzik/MovieApp/blob/master/readme/Screenshot_1582798427_framed.png?raw=true" width="330">
    </div>

*  ### __ViewModel__
   ViewModel is responsible for transferring data between view and model.
* ### __Dagger 2__
    – Implementation of dependency injection for communication between app modules<br/>
    – AndroidInjector applying for injecting into View components<br/>
    – Unit-testing simplifying
    <br/><br/>


# Applied technologies and libraries:


* ### __Model__

	* ### __Network__<br/>
	     __Retrofit 2__ <br/>
		– getting data from server into pojo-classes
     
	     __okHTTP__ <br/>
	        – caching data from the server to display the movie offline
		
	     __Moshi__ <br/>
		– converting  json to object
       
   * __Coroutines__ <br/>
      – managing asynchronous network queries<br/>
      – using instead of callbacks<br/>
      – providing light asynchronous operations

* ### __ViewModel__
   * __LiveData__ <br/>
      – observer-pattern implementation for View interaction
         
* ### __View__
   * __Fragments__ <br/>
     – interactive displaying and click reflecting
          
  *  __Data Binding__ <br/>
    – replace basic operations with UI (e.g. findViewById() ) to the XML

## All libraries: <br/>

* Android X
* Material Librarie 
* Android Jetpack
* Dagger 2
* Kotlin Coroutines
* Retrofit 2
* OkHTTP 3
* Moshi
* Glide

