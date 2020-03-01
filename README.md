![MovieApp](/readme/Photo%20for%20github.png?raw=true)
<br/><br/>
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
     <img src = "readme/Screenshot_1582798408_framed.png?raw=true" width="330">
     <img src = "readme/Screenshot_1582798427_framed.png?raw=true" width="330">
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
   * __Retrofit 2__ <br/>
     – getting data from server into pojo-classes
      
   * __SQLite__ <br/>
       – storing data fetched from server. User get data here if he is offline
       
   * __Coroutines__ <br/>
      – managing asynchronous database and network queries<br/>
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

