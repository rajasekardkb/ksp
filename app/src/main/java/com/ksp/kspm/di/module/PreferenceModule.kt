package com.ksp.kspm.di.module

import android.content.SharedPreferences
import com.ksp.kspm.data.preferences.StringPref
import com.ksp.kspm.data.preferences.*
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class PreferenceModule {
    companion object {
        const val PREF_NAME = "GoWhere_preferences"
        const val AUTH_TOKEN = "auth_token"
        const val FCM_TOKEN = "fcm_token"
        const val MOBILE_NUMBER = "mobile_number"
        const val SUB_CATEGORY_ID = "SUB_CATEGORY_ID"
        const val CATEGORY_ID = "CATEGORY_ID"
        const val COUNTRY_CODE = "country_code"
        const val EMAIL = "email"
        const val FIRST_NAME = "first_name"
         const val COLOR_CODE = "color_code"
        const val LAST_NAME = "last_name"
        const val PROF_PIC = "prof_pic"
        const val LAST_LAT = "last_lat"
        const val LAST_LONG ="last_long"
        const val NOTIFICATION_PERMISSION = "notification_permission"
        const val LOCATION_PERMISSION = "location_permission"
        const val TERMS = "TERMS"
        const val PRIVACY = "PRIVACY"
        const val WALKTHROUGH = "WALKTHROUGH"
        const val WALKTHROUGH_COMPLETED = "WALKTHROUGH_COMPLETED"
        const val LOGGEDIN_STATUS = "LOGGEDIN_STATUS"
        const val LOGIN_BEFORE_JOIN = "LOGIN_BEFORE_JOIN"
        const val LOGIN_BEFORE_JOIN_POPUP = "LOGIN_BEFORE_JOIN_POPUP"
        const val ID = "id"
        const val CREATED_DATE = "created_Date"
        const val glimpseFirst = "GLIMPSEFIRST"
        const val glimpseFirstPopup = "GLIMPSEFIRSTPOPUP"
        const val extendBeaconFirstPopup = "EXTENDBEACONFIRSTPOPUP"
        const val addBeaconFirstPopup = "ADDBEACONFIRSTPOPUP"
        const val addGlympseFirstPopup = "ADDGLYMPSEFIRSTPOPUP"
        const val tutorialViewFirstPopup = "TUTORIALVIEWFIRSTPOPUP"
        const val PRICE_MODEL = "price_model"
        const val FIRSTTIME_ADD_VIDEO_RESP = "first_time_addvideo_popup"
        const val TS_First_Dialog = "tsFirstDialog"
        const val USER_DETAILS = "USER_DETAILS"
        const val TOTAL_GLIMPSE_COUNT = "totalGlimpseCount"
        const val BeaconPlayFirstTime = "BeaconPlayFirstTime"
        const val BeaconPauseFirstTime = "BeaconPauseFirstTime"
        const val TAP_TO_ENABLE = "tap_to_enable"
        const val LOCATION_SELECTOR_INT = "locationSelectorInt"
        const val LOGOUT = "logout"
        const val REFER_SHARE_CONTENT = "referShareContent"
        const val CURRENT_GLIMPSE_COUNT = "currentGlimpseCount"
        const val IS_PAID_USER = "is_paid_user"

        const val LAST_VISITED_DATE = "LAST_VISITED_DATE"
        const val IS_FIRST_TIME = "IS_FIRST_TIME"
        const val USER_ID = "user_id"
        const val PROFILE_PRIVACY_PREF = "PROFILE_PRIVACY_PREF"
        const val PROFILE_TERMS_PREF = "PROFILE_TERMS_PREF"
        const val USER_DETAILS_PROFILE ="User_Details_Profile"
        const val REWARD_POINT_PROFILE = "REWARD_POINT_PROFILE"

        //Payement
        const val isPaymentInit = "isPaymentInit"


        @Provides
        @Named(LAST_VISITED_DATE)
        fun provideLastVisitedDate(sharedPreferences: SharedPreferences): StringPref {
            return StringPref(sharedPreferences, LAST_VISITED_DATE, "")
        }


        @Provides
        @Named(IS_FIRST_TIME)
        fun provideIsFirstTime(sharedPreferences: SharedPreferences): BooleanPref {
            return BooleanPref(sharedPreferences, IS_FIRST_TIME, false)
        }

        @Provides
        @Named(FIRSTTIME_ADD_VIDEO_RESP)
        fun provideFirstTimeAddProfileVideo(sharedPreferences: SharedPreferences): BooleanPref {
            return BooleanPref(sharedPreferences, FIRSTTIME_ADD_VIDEO_RESP, false)
        }


        @Provides
        @Named(TAP_TO_ENABLE)
        fun provideFirstTapToEnable(sharedPreferences: SharedPreferences): BooleanPref {
            return BooleanPref(sharedPreferences, TAP_TO_ENABLE, false)
        }


        @Provides
        @Named(IS_PAID_USER)
        fun provideIsPaidUser(sharedPreferences: SharedPreferences): BooleanPref {
            return BooleanPref(sharedPreferences, IS_PAID_USER, false)
        }


        @Provides
        @Named(NOTIFICATION_PERMISSION)
        fun provideNotificationPermission(preference: SharedPreferences): BooleanPref {
            return BooleanPref(preference, NOTIFICATION_PERMISSION, false)
        }


        @Provides
        @Named(LOCATION_PERMISSION)
        fun provideLocationPermission(preference: SharedPreferences): BooleanPref {
            return BooleanPref(preference, LOCATION_PERMISSION, false)
        }


        @Named(USER_DETAILS)
        @Provides
        fun provideUserDetails(preference: SharedPreferences): StringPref {
            return StringPref(preference, USER_DETAILS, "")
        }
        @JvmStatic
        @Named(USER_DETAILS_PROFILE)
        @Provides
        fun provideUserDetailsProfile(preference: SharedPreferences): StringPref {
            return StringPref(preference, USER_DETAILS_PROFILE, "")
        }
        @JvmStatic
        @Named(REWARD_POINT_PROFILE)
        @Provides
        fun provideRewardPointProfile(preference: SharedPreferences): IntPref {
            return IntPref(preference, REWARD_POINT_PROFILE, 0)
        }


        @Provides
        @Named(MOBILE_NUMBER)
        fun provideMobileNumber(preference: SharedPreferences): StringPref {
            return StringPref(preference, MOBILE_NUMBER, "9876543210")
        }


        @Provides
        @Named(SUB_CATEGORY_ID)
        fun provideSubCategotyId(preference: SharedPreferences): StringPref {
            return StringPref(preference, SUB_CATEGORY_ID, "")
        }


        @Provides
        @Named(CATEGORY_ID)
        fun provideCategotyId(preference: SharedPreferences): StringPref {
            return StringPref(preference, CATEGORY_ID, "")
        }


        @Provides
        @Named(EMAIL)
        fun provideEmail(preference: SharedPreferences): StringPref {
            return StringPref(preference, EMAIL, "jhon@example.com")
        }


        @Provides
        @Named(COUNTRY_CODE)
        fun provideCountryCode(preference: SharedPreferences): StringPref {
            return StringPref(preference, COUNTRY_CODE, "1")
        }


        @Provides
        @Named(FIRST_NAME)
        fun provideFirstName(preference: SharedPreferences): StringPref {
            return StringPref(preference, FIRST_NAME, "")
        }

        @Provides
        @Named(COLOR_CODE)
        fun provideColorCode(preference: SharedPreferences): IntPref {
            return IntPref(preference, COLOR_CODE, 1)
        }


        @Provides
        @Named(USER_ID)
        fun provideUserId(preference: SharedPreferences): StringPref {
            return StringPref(preference, USER_ID, "")
        }


        @Provides
        @Named(LAST_NAME)
        fun provideLastName(preference: SharedPreferences): StringPref {
            return StringPref(preference, LAST_NAME, "")
        }


        @Provides
        @Named(PROF_PIC)
        fun provideProfPic(preference: SharedPreferences): StringPref {
            return StringPref(preference, PROF_PIC, "")
        }


        @Provides
        @Named(TERMS)
        fun provideTermsPref(preference: SharedPreferences): StringPref {
            return StringPref(
                preference,
                TERMS,
                "No com.torvis.gowhereuser.home.core.domain.restaurantDetails.Data Available Please Check Your Internet Connection"
            )
        }


        @Provides
        @Named(PRIVACY)
        fun providePrivacy(preference: SharedPreferences): StringPref {
            return StringPref(
                preference,
                PRIVACY,
                "No com.torvis.gowhereuser.home.core.domain.restaurantDetails.Data Available Please Check Your Internet Connection"
            )
        }


        @Provides
        @Named(REFER_SHARE_CONTENT)
        fun provideReferShareContent(preference: SharedPreferences): StringPref {
            return StringPref(
                preference, REFER_SHARE_CONTENT, ""
            )
        }


        @Provides
        @Named(WALKTHROUGH)
        fun provideWalkThroughPref(preference: SharedPreferences): StringPref {
            return StringPref(preference, WALKTHROUGH, "")
        }


        @Provides
        @Named(LAST_LAT)
        fun provideLastLat(preference: SharedPreferences) : FloatPref{
            return FloatPref(preference, LAST_LAT,0.0F)
        }

        @JvmStatic
        @Provides
        @Named(LAST_LONG)
        fun provideLastLong(preference: SharedPreferences) : FloatPref{
            return FloatPref(preference, LAST_LONG,0.0F)
        }

        @JvmStatic
        @Provides
        @Named(WALKTHROUGH_COMPLETED)
        fun provideWalkThroughCompleted(preference: SharedPreferences): BooleanPref {
            return BooleanPref(preference, WALKTHROUGH_COMPLETED, false)
        }


        @Provides
        @Named(LOGGEDIN_STATUS)
        fun providesLoggedInStatus(preference: SharedPreferences): BooleanPref {
            return BooleanPref(preference, LOGGEDIN_STATUS, false)
        }


        @Provides
        @Named(AUTH_TOKEN)
        fun providesAuthToken(preference: SharedPreferences): StringPref {
            return StringPref(preference, AUTH_TOKEN, "")
        }

        @Provides
        @Named(FCM_TOKEN)
        fun providesFCMToken(preference: SharedPreferences): StringPref {
            return StringPref(preference, FCM_TOKEN, "")
        }


        @Provides
        @Named(LOGIN_BEFORE_JOIN)
        fun provideLoginBeforeJoinPref(preference: SharedPreferences): StringPref {
            return StringPref(preference, LOGIN_BEFORE_JOIN, "")
        }


        @Provides
        @Named(LOGIN_BEFORE_JOIN_POPUP)
        fun provideLoginBeforeJoinPopupPref(preference: SharedPreferences): StringPref {
            return StringPref(preference, LOGIN_BEFORE_JOIN_POPUP, "")
        }


        @Provides
        @Named(ID)
        fun provideId(preference: SharedPreferences): StringPref {
            return StringPref(preference, ID, "")
        }


        @Provides
        @Named(CREATED_DATE)
        fun provideCreatedDate(preference: SharedPreferences): StringPref {
            return StringPref(preference, CREATED_DATE, "")
        }


        @Provides
        @Named(glimpseFirst)
        fun provideGlimpseFirst(preference: SharedPreferences): BooleanPref {
            return BooleanPref(preference, glimpseFirst, true)
        }


        @Provides
        @Named(glimpseFirstPopup)
        fun provideGlimpseFirstPopup(preference: SharedPreferences): BooleanPref {
            return BooleanPref(preference, glimpseFirstPopup, true)
        }


        @Provides
        @Named(extendBeaconFirstPopup)
        fun provideExtendBeaconFirstPopup(preference: SharedPreferences): BooleanPref {
            return BooleanPref(preference, extendBeaconFirstPopup, false)
        }


        @Provides
        @Named(addBeaconFirstPopup)
        fun provideAddBeaconFirstPopup(preference: SharedPreferences): BooleanPref {
            return BooleanPref(preference, addBeaconFirstPopup, false)
        }


        @Provides
        @Named(addGlympseFirstPopup)
        fun provideAddGlympseFirstPopup(preference: SharedPreferences): BooleanPref {
            return BooleanPref(preference, addGlympseFirstPopup, false)
        }


        @Provides
        @Named(BeaconPlayFirstTime)
        fun provideBeaconPlayFirstTime(preference: SharedPreferences): BooleanPref {
            return BooleanPref(preference, BeaconPlayFirstTime, false)
        }


        @Provides
        @Named(BeaconPauseFirstTime)
        fun provideBeaconBeaconPauseFirstTime(preference: SharedPreferences): BooleanPref {
            return BooleanPref(preference, BeaconPauseFirstTime, false)
        }


        @Provides
        @Named(tutorialViewFirstPopup)
        fun provideTutorialViewFirstPopup(preference: SharedPreferences): BooleanPref {
            return BooleanPref(preference, tutorialViewFirstPopup, false)
        }


        @Provides
        @Named(PRICE_MODEL)
        fun providePriceMasterModel(preference: SharedPreferences): StringPref {
            return StringPref(preference, PRICE_MODEL, "")
        }


        @Provides
        @Named(TS_First_Dialog)
        fun provideTSFirstTimeDialog(preference: SharedPreferences): BooleanPref {
            return BooleanPref(preference, TS_First_Dialog, false)
        }


        @Provides
        @Named(TOTAL_GLIMPSE_COUNT)
        fun provideTotalGlimpseCount(preference: SharedPreferences): IntPref {
            return IntPref(preference, TOTAL_GLIMPSE_COUNT, 0)
        }


        @Provides
        @Named(LOCATION_SELECTOR_INT)
        fun provideLocationSelectorInt(preference: SharedPreferences): IntPref {
            return IntPref(preference, LOCATION_SELECTOR_INT, 0)
        }


        @Provides
        @Named(CURRENT_GLIMPSE_COUNT)
        fun provideCurrentGlimpseCount(preference: SharedPreferences): IntPref {
            return IntPref(preference, CURRENT_GLIMPSE_COUNT, 0)
        }


        @Provides
        @Named(LOGOUT)
        fun provideLogout(preference: SharedPreferences): LogoutPref {
            return LogoutPref(preference)
        }

        // --------- To Clear Preference ------------------- //

        @Provides
        fun provideClearPreference(preference: SharedPreferences): ClearPref {
            return ClearPref(preference)
        }

        @JvmStatic
        @Provides
        @Named(PROFILE_TERMS_PREF)
        fun provideProfileTermsPref(preference: SharedPreferences): StringPref {
            return StringPref(
                preference,
                PROFILE_TERMS_PREF,
                "No data available."
            )
        }

        @JvmStatic
        @Provides
        @Named(PROFILE_PRIVACY_PREF)
        fun provideProfilePrivacyPref(preference: SharedPreferences): StringPref {
            return StringPref(
                preference,
                PROFILE_PRIVACY_PREF,
                "No data available."
            )
        }

        //Payment
        @Provides
        @Named(isPaymentInit)
        fun provideIsPaymentInitPref(preference: SharedPreferences): BooleanPref{
            return BooleanPref(preference, isPaymentInit,false)
        }

    }
}