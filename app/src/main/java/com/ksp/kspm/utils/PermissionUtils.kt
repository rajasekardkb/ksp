package com.ksp.kspm.utils

import android.content.Context
import android.content.pm.PackageManager


/**
 * Created by Vaghela Mithun R. on 29/01/21.
 * vaghela.mithun@gmail.com
 */
class PermissionUtils(val context: Context?) {

    private fun checkPermission(permission: String): Boolean {
        val res: Int = context!!.checkCallingOrSelfPermission(permission)
        return res == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPermission(permissions: Array<String>): Boolean {
        var isGranted: Boolean = false
        permissions.forEach {
            isGranted = checkPermission(it)
        }
        return isGranted
    }
}