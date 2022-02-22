package com.casad.common.resources

import com.blankj.utilcode.util.StringUtils

fun Int.toXmlString(): String {
    return StringUtils.getString(this)
}