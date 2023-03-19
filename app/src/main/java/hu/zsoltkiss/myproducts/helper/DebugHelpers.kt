package hu.zsoltkiss.myproducts.helper

import hu.zsoltkiss.myproducts.BuildConfig
import io.reactivex.rxjava3.core.Observable

fun debugPrint(message: Any?, prefix: String = "====", step: Int? = null) {
    if (BuildConfig.DEBUG) {
        println("${step?.let { "$it " } ?: ""}$prefix $message")
    }
}

fun <T : Any> Observable<T>.printOnEach(
    step: Int? = null,
    prefix: String = "====",
    action: (T) -> String = { "OnNextNotification[$it]" }
): Observable<T> = doOnEach { debugPrint("${it.value?.let { action(it) } ?: it}", prefix, step) }