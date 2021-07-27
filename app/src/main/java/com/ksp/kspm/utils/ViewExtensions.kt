@file:Suppress("DEPRECATION")

package com.ksp.kspm.utils


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.SpannableStringBuilder
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerFragment
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern


//Toolbar title
fun DaggerFragment.setTitle(title: String) {
    (activity as AppCompatActivity).supportActionBar!!.title = title
}

//View hidep
fun View.hide() {
    visibility = View.GONE
}

//View visible
fun View.show() {
    visibility = View.VISIBLE
}

//View inVisible
fun View.inVisible() {
    visibility = View.INVISIBLE
}

//time to string
fun Long.toMMSS(): String {

    return String.format(
        "%02d:%02d",
        TimeUnit.MILLISECONDS.toMinutes(this) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(this)), // The change is in this line
        TimeUnit.MILLISECONDS.toSeconds(this) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this))
    )
}

//time to string
fun Long.toHourMinute(): String {

    return String.format(
        "%02dH:%02dM",
        TimeUnit.MILLISECONDS.toMinutes(this) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(this)), // The change is in this line
        TimeUnit.MILLISECONDS.toSeconds(this) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this))
    )
}

fun Long.toHHMMSS(): String {

    return String.format(
        "%02dH:%02dM:%02dS",
        TimeUnit.MILLISECONDS.toMinutes(this) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(this)), // The change is in this line
        TimeUnit.MILLISECONDS.toSeconds(this) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this)),
        TimeUnit.MILLISECONDS.toSeconds(this) -
                TimeUnit.MINUTES.toSeconds(this)
    )
}


fun DaggerAppCompatActivity.setUpToolBar(toolbar: Toolbar) {
    setSupportActionBar(toolbar)
}


/**
 * Set up the toolbar with back arrow
 */
fun DaggerAppCompatActivity.setUpToolBarWithBackArrow(toolbar: Toolbar) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    // toolbar.setNavigationIcon(R.drawable.ic_back_black)

}


//Custom toast
//fun DaggerAppCompatActivity.showCustomToast(message: String) {
//    val inflater: LayoutInflater = layoutInflater
//    val view =
//        inflater.inflate(R.layout.layout_custom_toast, findViewById(R.id.custom_toast_container))
//    val toastText: TextView = view.findViewById(R.id.tv_toast_message)
//    toastText.text = message
//    val toast = Toast(this)
//    toast.setGravity(Gravity.TOP, 0, 0)
//    toast.duration = Toast.LENGTH_LONG
//    toast.view = view
//    toast.show()
//}

//Toast
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

//Toast
fun Context.showToastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

/*

//Divider Item decoration
fun Context.divider(): DividerItemDecoration {
    val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
    ContextCompat.getDrawable(this, R.drawable.divider)
        ?.let { dividerItemDecoration.setDrawable(it) }
    return dividerItemDecoration
}//Divider Item decoration
*/

//is valid email
fun String.isValidEmail(): Boolean {
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

//is valid phone number
fun String.isValidPhoneNumber(): Boolean {
    if (!Pattern.matches("[a-zA-Z]+", this)) {
        return this.length in 7..13
    }
    return false
}

fun String.removeHyphenAndSpace(): String {
    val removeHyphen = this.replace("-", "")
    return removeHyphen.replace(" ", "")
}

@SuppressLint("SimpleDateFormat")
@Throws(ParseException::class)
fun Long.milliToString(): String {
    return if (this != 0L) {
        val formatter: DateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val date = formatter.format(this)
        date.toString()
    } else ""
}

@SuppressLint("SimpleDateFormat")
@Throws(ParseException::class)
fun String.toTimeMilli(): Long {
    return if (!this.isNullOrEmpty()) {
        return try {
            val formatter: DateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault())
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val date = formatter.parse(this) as Date
//            formatter.timeZone = TimeZone.getDefault()
            date.time
        } catch (e: Exception) {
            val formatter: DateFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val date = formatter.parse(this) as Date
//            formatter.timeZone = TimeZone.getDefault()
            date.time
        }
    } else 0L

}

fun getCurrentTime(): String {
    val time = Calendar.getInstance().time
    val formatter: DateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault())
    return formatter.format(time)
}

fun getGreaterDate(mapLastSeenDate: String, beaconPlaceDate: String): Boolean {
    val formatter: DateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault())
    val convertedDate: Date?
    val convertedDate2: Date?
    convertedDate = formatter.parse(mapLastSeenDate)
    convertedDate2 = formatter.parse(beaconPlaceDate)
    return convertedDate2.after(convertedDate)
}

//@SuppressLint("SimpleDateFormat")
//@Throws(ParseException::class)
//fun String.utcToNotificationListTime(): String {
//    return if (!this.isNullOrEmpty()) {
//        return try {
//            val formatter: DateFormat =
//                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
//            formatter.timeZone = TimeZone.getTimeZone("UTC")
//            val date = formatter.parse(this) as Date
////            formatter.timeZone = TimeZone.getDefault()
//            val day = date.day.toString()
//            val time = date.time.toString()
//            ${day}
//        } catch (e: Exception) {
//            val formatter: DateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.getDefault())
//            formatter.timeZone = TimeZone.getTimeZone("UTC")
//            val date = formatter.parse(this) as Date
////            formatter.timeZone = TimeZone.getDefault()
//
//            date.time
//        }
//    } else ""
//}

fun uTCToLocal(
    dateFormatInPut: String,
    dateFomratOutPut: String,
    datesToConvert: String
): String {
    var dateToReturn = datesToConvert
    val sdf = SimpleDateFormat(dateFormatInPut, Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    val gmt: Date?
    val sdfOutPutToSend =
        SimpleDateFormat(dateFomratOutPut, Locale.getDefault())
    sdfOutPutToSend.timeZone = TimeZone.getDefault()
    try {
        gmt = sdf.parse(datesToConvert)
        dateToReturn = sdfOutPutToSend.format(gmt!!)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return dateToReturn
}

fun timeToLocal(
    dateFormatInPut: String,
    dateFomratOutPut: String,
    datesToConvert: String
): String {
    var dateToReturn = datesToConvert
    val sdf = SimpleDateFormat(dateFormatInPut, Locale.getDefault())
    val gmt: Date?
    val sdfOutPutToSend =
        SimpleDateFormat(dateFomratOutPut, Locale.getDefault())
    sdfOutPutToSend.timeZone = TimeZone.getDefault()
    try {
        gmt = sdf.parse(datesToConvert)
        dateToReturn = sdfOutPutToSend.format(gmt!!)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return dateToReturn
}


fun String.utcToNotificationListTime(): String {
    val formatter: DateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val date = formatter.parse(this) as Date
//            formatter.timeZone = TimeZone.getDefault()
    date.time

    val cal = Calendar.getInstance(Locale.getDefault())
    val curCal = Calendar.getInstance(Locale.getDefault())
    if (date.time == 0L) return ""
    cal.timeInMillis = date.time

    val timeFormat = "h:mm aa"
    val timeFormatYear = "M/dd/yy"
    val today = "Today at "
    val yesterday = "Yesterday at "

    return when {
        curCal.get(Calendar.DATE) == cal.get(Calendar.DATE) -> today +
                android.text.format.DateFormat.format(
                    timeFormat,
                    cal
                )
        curCal.get(Calendar.DATE) - cal.get(Calendar.DATE) == 1 -> yesterday + android.text.format.DateFormat.format(
            timeFormat,
            cal
        )
        else -> "${android.text.format.DateFormat.format(timeFormatYear, cal)}"
    }
}


//To check string contains
val String.containsLatinLetter: Boolean
    get() = matches(Regex(".*[A-Za-z].*"))

val String.containsDigit: Boolean
    get() = matches(Regex(".*[0-9].*"))

val String.isAlphanumeric: Boolean
    get() = matches(Regex("[A-Za-z0-9]*"))

val String.hasLettersAndDigits: Boolean
    get() = containsLatinLetter && containsDigit

val String.isIntegerNumber: Boolean
    get() = toIntOrNull() != null

val String.toDecimalNumber: Boolean
    get() = toDoubleOrNull() != null

fun Boolean.toInt() = if (this) 1 else 0

/**
 * To get the activity context from view inside it
 */
fun View.getActivity(): Activity? {
    var context = this.context   // getting view context
    while (context is ContextWrapper) { // checking the wrapped context of the view
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}


//launch activity
fun <T> Context.launchActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun String.toEditable(): Editable = SpannableStringBuilder(this)

fun View.hideKeyboard() {
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard() {
    val inputMethodManager =
        context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.showSoftInput(this, 0)
}


fun Context.hideKeyboard(view: View) {
    val imm: InputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

inline fun <reified T> Gson.fromJson(json: String): T =
    this.fromJson(json, object : TypeToken<T>() {}.type)

inline fun <reified T> Context.startActivity(func: (Intent) -> Unit) {
    val intent = Intent(this, T::class.java)
    func(intent)
    this.startActivity(intent)
}

fun htmlFormat(data: String): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        (Html.fromHtml(
            data,
            Html.FROM_HTML_MODE_COMPACT
        )).toString()
    } else {
        (Html.fromHtml(data)).toString()
    }
}

fun Context.convertDpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        this.resources.displayMetrics
    )
}


fun calculateNoOfColumns(context: Context, columnWidthDp: Float): Int {
// For example columnWidthdp=180
    val displayMetrics = context.resources.displayMetrics
    val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
    return (screenWidthDp / columnWidthDp + 0.5).toInt()
}

fun RecyclerView.autoFitColumns(columnWidth: Int) {
    val displayMetrics = this.context.resources.displayMetrics
    val noOfColumns = ((displayMetrics.widthPixels / displayMetrics.density) / columnWidth).toInt()
    this.layoutManager = GridLayoutManager(this.context, noOfColumns)
}


fun View.removeDrawable() {
    if (this is TextView) {
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
    } else if (this is ImageView) {
        this.setImageDrawable(null)
    }
}

/**
 *  Stop and Hide the SwipeRefreshWidget is actively showing refresh
 *         progress.
 */


