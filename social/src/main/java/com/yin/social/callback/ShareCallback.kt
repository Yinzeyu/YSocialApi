package com.yin.social.callback

import com.yin.social.config.PlatformType


/**
 * description:分享的callback
 *@date 2019/7/15
 *@author: yzy.
 */
data class ShareCallback(
    var onSuccess: ((type: PlatformType) -> Unit)? = null,
    override var onErrors: ((type: PlatformType, errorCode: Int, errorMsg:String?) -> Unit)? = null,
    var onCancel: ((type: PlatformType) -> Unit)? = null
) : OperationCallback