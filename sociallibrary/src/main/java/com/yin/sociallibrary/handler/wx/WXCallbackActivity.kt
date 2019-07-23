package com.yin.sociallibrary.handler.wx

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.yin.sociallibrary.PlatformManager
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

/**
 * description: 微信登陆分享 activity
 *@date 2019/7/15
 *@author: yzy.
 */
class WXCallbackActivity : Activity(), IWXAPIEventHandler {

  private lateinit var mWXHandler: WXHandler
  private lateinit var mWXCircleHandler: WXHandler

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    window.setFlags(
      WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN
    )

    initAndPerformIntent()
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    initAndPerformIntent()
  }

  override fun onResp(resp: BaseResp?) {
    if (resp != null) {
      try {
        mWXHandler.wxEventHandler.onResp(resp)
        mWXCircleHandler.wxEventHandler.onResp(resp)
      } catch (var3: Exception) {
      }

    }
    finish()
    overridePendingTransition(0, 0)
  }

  override fun onReq(req: BaseReq?) {
    mWXHandler.wxEventHandler.onReq(req)

    this.finish()
    overridePendingTransition(0, 0)
  }

  private fun initAndPerformIntent() {

    if (PlatformManager.currentHandler == null){
      finish()
    }

    PlatformManager.currentHandler?.let {
      mWXCircleHandler = it as WXHandler
      mWXHandler = it
      mWXHandler.wxAPI?.handleIntent(intent, this)
    }
  }
}