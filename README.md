![MovieApp](https://github.com/Urdzik/MovieApp/blob/readme-branch/readme/Photo%20for%20github.png?raw=true)


[![Kotlin](https://img.shields.io/badge/Kotlin-1.3.61-blue.svg)](https://kotlinlang.org)
[![Dagger2](https://img.shields.io/badge/Dagger%202-2.26-red.svg)](https://github.com/google/dagger)
[![Material design](https://img.shields.io/badge/Material%20Design-1.2.0--alpha%205-%237464f2)](https://material.io)

<h3 size="1">Application which retrieves data from Webserver (via Retrofit), saves it into Room and get from it if user is offline. There are applying MVVM architecture pattern and Dagger 2 example.</h3><br/>

<font size="6">Overview:</font><br/>
=======================================================


* ### __Model__
   Model is implemented as Repository pattern. Firstly it begins from internet connection checking. Consequently if it's alive we're retrieving data from the server (by using Retrofit 2) and inserting into the SQLite database. Otherwise we're trying to fetch data from the SQLite itself.
* ### __View__
     View is realised as 2 fragments. First one contains RecyclerView, second one depends on clicks on recycler-items and finally displays detailed data fetched from the Model. It implements state saving reflected on configuration changes.
    <br/><br/>

    <div align = "center">
     <img src = "https://github.com/Urdzik/MovieApp/blob/readme-branch/readme/Screenshot_1582798408_framed.png?raw=true" width="330">
     <img src = "https://github.com/Urdzik/MovieApp/blob/readme-branch/readme/Screenshot_1582798427_framed.png?raw=true" width="330">
    </div>

*  ### __ViewModel__
   ViewModel is responsible for transferring data between view and model.
* ### __Dagger 2__
    – Implementation of dependency injection for communication between app modules<br/>
    – AndroidInjector applying for injecting into View components<br/>
    – Unit-testing simplifying
    <br/><br/>


<font size="6">Applied technologies and libraries:</font><br/>
=======================================================

* ### __Model__

     <font size="3"><b>Retrofit 2 </b></font><br/>
          – getting data from server into pojo-classes

     <font size="3"><b>SQLite</b></font><br/>
          – storing data fetched from server. User get data here if he is offline

    <font size="3"><b>Coroutines</b></font><br/>
          – managing asynchronous database and network queries<br/>
          – using instead of callbacks<br/>
          – providing light asynchronous operations

* ### __ViewModel__
    <font size="3"><b>LiveData</b></font><br/>
          – observer-pattern implementation for View interaction

* ### __View__
   <font size="2"><b>Fragments</b></font><br/>
          – interactive displaying and click reflecting
          
   <font size="2"><b>Data Binding</b></font><br/>
          – replace basic operations with UI (e.g. findViewById() ) to the XML

