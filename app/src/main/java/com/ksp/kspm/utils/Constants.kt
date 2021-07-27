package com.ksp.kspm.utils

object Constants {
    //for location access permission at HomeActivity
    const val PERMISSION_GRANTED = "GRANTED"
    const val PERMISSION_ASKED = "ASKED"
    const val PERMISSION_DENIED = "DENIED"
    const val EXT_DEAL_IDS: String = "dealIds"
    const val EXT_DEAL: String = "deal"
    const val EXT_STORY: String = "deal_story_extra"
    const val EXT_DEAL_POSITION: String = "deal_position"
    const val EXT_ESTABLISHMENT_ID = "establishmentId"
    const val DEAL_REQUEST_CODE = 1010
    const val IN_APP_UPDATE_ENABLE = 1
    const val MILS_LIST: String = "mils_list"
    const val IN_APP_UPDATE_REQUEST = 1009


    /**
     * Intent key for passing current latitude
     */
    const val CURRENT_LAT = "CURRENT_LAT"

    /**
     * Intent key for passing current longitude
     */
    const val CURRENT_LON = "CURRENT_LON"

    /**
     * Intent key for passing update view as [Boolean],
     * to identify whether to update the ui or not
     */
    const val UPDATE_VIEW = "UPDATE_VIEW"

    /**
     * Intent key for passing category id as [String]
     */
    const val CATEGORY_ID = "CATEGORY_ID"

    /**
     * Intent key for passing sub category id as [String]
     */
    const val SUBCATEGORY_ID = "SUBCATEGORY_ID"

    /**
     *
     */
    const val ESTABLISHMENT_ID = "ESTABLISHMENT_ID"

    /**
     * Intent key for passing address in the defined format as [String]
     * for example: Chicago,IL
     */
    const val FORMATTED_ADDRESS = "FORMATTED_ADDRESS"

    const val OPEN_NEXT = "OPEN_NEXT"

    const val SAVE_A_PLATE = "SAVE_A_PLATE"

    const val VIEW_ALL_DEALS = "VIEW_ALL_DEALS"

    const val VIEW_TYPE = "VIEW_TYPE"

    const val LIST_DATA = "LIST_DATA"
    const val EXT_NAVIGATE_FROM = "navigate_from"




    /***Profile navigation intents*/
    const val PROFILE_FNAME = "PROFILE_FNAME"
    const val PROFILE_LNAME = "PROFILE_LNAME"
    const val PROFILE_EMAIL = "PROFILE_EMAIL"
    const val PROFILE_NUMBER = "PROFILE_NUMBER"
    const val PROFILE_CCP = "PROFILE_CCP"
    const val PROFILE_IMAGE_STATUS = "PROFILE_IMAGE_STATUS"
    const val PROFILE_LOCATION_STATUS ="PROFILE_LOCATION_STATUS"
    const val PROFILE_TERMS = "PROFILE_TERMS"
    const val PROFILE_PRIVACY = "PROFILE_PRIVACY"
    const val PROFILE_TERMS_PRIVACY = "PROFILE_TERMS_PRIVACY"



    //intent constant
    const val RESTAURANT_ID = "RESTAURANT_ID"
    const val FAVOURITE_STATUS = "FAVOURITE_STATUS"

    //Category Ids for general purpose
    //these are constants which are not intended to changes, if changed might misbehave
    /**
     * CategoryId for Restaurant
     */
    const val CA0004 = "CA0004" //Restaurant

    /**
     * CategoryId for Bars and Night Clubs
     */
    const val CA0005 = "CA0005" //Bars and Night Clubs

    /**
     * CategoryId for Things to do
     */
    const val CA0006 = "CA0006" //Things to do

    /**
     * CategoryId for Salon and Spa
     */
    const val CA0007 = "CA0007" //Salon spa

    /**
     * CategoryId for Health and Wellness
     */
    const val CA0008 = "CA0008" //Health and Wellness

    /**
     * CategoryId for Home & Professional Service
     */
    const val CA0009 = "CA0009" //Home & Professional Service

    /**
     * CategoryId for Save a Plate
     */
    const val CA0003 = "CA0003" //Save a Plate


    /**
     * Intent key to define a goto string in extras
     */
    const val GO_TO = "GO_TO"

    //numbers as constant, to fix detekt "Magic Numbers" rule
    const val NUMERIC_9 = 9


    const val ONE_HOUR_MILLISECOND = 3600000
    const val ONE_MINUTE_MILLISECOND = 60000
    const val ONE_SECOND_MILLISECOND = 1000


    const val SIXTY_SECOND = 60
    const val SIXTY_MINUTES = 60
    const val TWENTY_FOUR_HOURS = 24


    const val COMMON_PADDING_8F = 8f
    const val PADDING_16F = 16F
    const val MAX_DATA_PER_PAGE_5 = 5
    const val MAX_DEALS_GRID_4 = 4

    //top picks
    const val MAX_DEALS_6 = 6

    //Constant id for rewards
    const val buyDeal = "RE00001"
    const val shareDeal = "RE00002"
    const val shareEstablishment = "RE00003"
    const val inviteFriends = "RE00004"

    //For QR Code getting error
    const val qr_error = "QR doesn't match"

    const val FROM_NOTIFICATION_TO_MAP = "isComeFromNotificationForMap"
    const val NOTIFICATION_BUNDLE = "notification_bundle"
    const val NOTIFICATION_GOTO = "notification_goto"
    const val NOTIFICATION_ID = "notification_id"
    const val NOTIFICATION_ACTION = "notification_action"
    const val NOTIFICATION_DATA = "notification_bundle"
    const val FROM_NOTIFICATION_TO_MY_PROFILE = "redirected_from_notification_to_my_profile"
}