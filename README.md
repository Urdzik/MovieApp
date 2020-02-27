![MovieApp](https://github.com/Urdzik/MovieApp/blob/readme-branch/readme/Photo%20for%20github.png?raw=true)

[![Kotlin](https://img.shields.io/badge/Kotlin-1.3.61-blue.svg)](https://kotlinlang.org)
[![Dagger2](https://img.shields.io/badge/Dagger%202-2.26-red.svg)](https://github.com/google/dagger)
[![Room](https://img.shields.io/badge/Room-2.2.4-brightgreen.svg)](https://developer.android.com/topic/libraries/architecture/room)
[![Android Jetpack](https://img.shields.io/badge/-Android%20Jetpack-%2300db8a.svg)](https://developer.android.com/jetpack)


### Application which retrieves data from Webserver (via Retrofit), saves it into Room and get from it if user is offline. There are applying MVVM architecture pattern and Dagger 2 example.


# Overview:

* ### __Model__
   Model is implemented as Repository pattern. Firstly it begins from internet connection checking. Consequently if it's alive we're retrieving data from the server (by using Retrofit 2) and inserting into the SQLite database. Otherwise we're trying to fetch data from the SQLite itself.
 * ### __View__
   View is realised as 2 fragments. First one contains RecyclerView, second one depends on clicks on recycler-items and finally displays detailed data fetched from the Model.
It implements state saving reflected on configuration changes.
* ### __ViewModel__
  ViewModel is responsible for transferring data between view and model.
 * ### __Dagger 2__
    - Implementation of dependency injection for communication between app modules
    - AndroidInjector applying for injecting into View components
    - Unit-testing simplifying


<div align = "center">
<img src = "readme/Screenshot_1582798408_framed.png" width="330">
<img src = "readme/Screenshot_1582798427_framed.png" width="330">
</div>

# Applied technologies and libraries:

* ## __Model__
    ### Retrofit 2  
    – getting data from server into pojo-classes
    ### SQLite
    – storing data fetched from server. User get data here if he is offline
    ### Coroutines
    – managing asynchronous database and network queries<br/>
    – using instead of callbacks<br/>
    – providing light asynchronous operations

* ## __ViewModel__
    ### LiveData
    – observer-pattern implementation for View interaction


* ## __View__
    ### Fragments
    – interactive displaying and click reflecting
    ### Data Binding
    – replace basic operations with UI (e.g. findViewById() ) to the XML

