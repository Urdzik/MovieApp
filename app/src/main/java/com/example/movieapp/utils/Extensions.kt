package com.example.movieapp.utils

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

inline fun <reified T : ViewModel> Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
}

fun EditText.toFlowable(): Flowable<CharSequence> {
    return Flowable.fromPublisher { subscriber ->
        doAfterTextChanged {
            subscriber.onNext(it)
        }
    }
}

fun <T> Flowable<CharSequence>.withParams(
    minLength: Int = 2,
    debounce: Long = 300,
    timeUnit: TimeUnit = TimeUnit.MILLISECONDS,
    switchMapper: ((CharSequence) -> Flowable<T>)? = null,
    doOnNext: (() -> Unit)? = null
): Flowable<T> {
    return subscribeOn(AndroidSchedulers.mainThread())
        .filter {
            it.length >= minLength
        }
        .debounce(debounce, timeUnit)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext { doOnNext?.invoke() }
        .observeOn(Schedulers.io())
        .switchMap {
            switchMapper?.let { mapper -> return@switchMap mapper(it) }
            return@switchMap Flowable.just(it) as Flowable<T>
        }
        .observeOn(AndroidSchedulers.mainThread())
}