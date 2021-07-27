package com.ksp.kspm.utils

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

//find more details here https://stackoverflow.com/questions/47854598/livedata-remove-observer-after-first-callback
fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner,observer: Observer<T>){
    observe(lifecycleOwner,object :Observer<T>{
        override fun onChanged(t: T) {
            observer.onChanged(t)
            removeObserver(this)
        }

    })
}

/**
 * To share the given text through intent
 *
 * @param msg is the content that needs to be shared
 */
fun Context.textShareIntent(msg: String) {
    Intent(Intent.ACTION_SEND).let { intent ->
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, msg)
        startActivity(Intent.createChooser(intent, "Share via" /*need content*/))
    }

}

/**
 * this to sub list the current list if the list's size is greater than
 * @param value is the elements to be returned from current list
 * @return current list either  sublist based on the size
 */
fun <T> List<T>.subListIfGreaterThan(value: Int = 4): List<T> {
    return if (this.isListGreaterThanOrEquals(value + 1)) {
        this.subList(0, value)
    } else {
        this
    }
}

/**
 * To check the current list greater than or equals
 * @param value to check with list size, for our project the default value is 4, pass value to override
 * @return Boolean based on the result
 */
fun <T> List<T>.isListGreaterThanOrEquals(value: Int = 4): Boolean {
    return this.size >= value
}

/**
 * Check the current list is not null and not empty
 *
 * @return Boolean if the list is not null and not empty
 */
fun <T> List<T>?.isNotNullAndNotEmpty(): Boolean {
    return this?.isNotEmpty() ?: false
}
/**
 * This is the actual logic of above function,
 * above is kotlin simplified code,
 *
 * for better understanding
 * fun <T> List<T>?.isNotEmptyAndNotNull():Boolean {
return if (this == null){
false
}else if(this.isEmpty()){
false
}else{
true
}
}
 *
 * or we could simpily use Collections<?>
 */




