package com.yin.sociallibrary.callback

import com.yin.sociallibrary.config.PlatformType


/**
 * description:支付的callback
 *@date 2019/7/15
 *@author: yzy.
 */
data class PayCallback(
  var onSuccess: ((type: PlatformType) -> Unit)? = null,
  override var onErrors: ((type: PlatformType, errorCode: Int, errorMsg: String?) -> Unit)? = null,
  var onCancel: ((type: PlatformType) -> Unit)? = null
) : OperationCallback