package com.yin.android

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aimymusic.aimysociallib.URL
import com.yin.social.Social
import com.yin.social.config.PlatformType
import com.yin.social.utils.CallbackDataUtil
import kotlinx.android.synthetic.main.activity_wxshare.*

class WXActivity : AppCompatActivity() {

  companion object {
    fun startActivity(context: Context) {
      val intent = Intent(context, WXActivity::class.java)
      intent.putExtra("className", context::class.java.simpleName)
      context.startActivity(intent)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_wxshare)
    initEvent()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    com.yin.social.Social.onActivityResult(requestCode, resultCode, data)
  }

  private fun initEvent() {
    btn_wx_share_img.setOnClickListener {
      com.yin.social.Social.share(this, getType(),
        img = createBitmap(),                  // 除了分享文本外，其他分享img都必须传
        onSuccess = { type ->
//          toast("$type 分享成功")
        },
        onError = { type, code, msg ->
//          toast("$type:$code == $msg")
        },
        onCancel = { _ -> })
    }
    btn_wx_share_music.setOnClickListener {
      com.yin.social.Social.share(this, getType(),
        img = createBitmap(),                  // img必须传
        title = "test",
        musicUrl = URL.AUDIO_URL, // 必须传
        musicDataUrl = "",   // 如果有就传
        description = "测试",
        onSuccess = { type ->
//          toast("$type 分享成功")
        },
        onError = { type, code, msg ->
//          toast("$type:$code == $msg")
        },
        onCancel = { _ -> })
    }
    btn_wx_share_video.setOnClickListener {
      com.yin.social.Social.share(this, getType(),
        img = createBitmap(),                  // img必须传
        title = "test",
        videoUrl = URL.VIDEO_URL, // 必须传
        videoLowBandUrl = URL.VIDEO_URL,   // 如果分享平台是微信，可以传videoUrl或者videoLowBandUrl其一
        description = "测试",
        onSuccess = { type ->
//          toast("$type 分享成功")
        },
        onError = { type, code, msg ->
//          toast("$type:$code == $msg")
        },
        onCancel = { _ -> })
    }
    btn_wx_share_text.setOnClickListener {
    com.yin.social.Social.share(this, getType(),
        text = "测试",   // 必传
        onSuccess = { type ->
//          toast("$type 分享成功")
        },
        onError = { type, code, msg ->
//          toast("$type:$code == $msg")
        },
        onCancel = { _ -> })
    }
    btn_wx_share_web.setOnClickListener {
      com.yin.social.Social.share(this, getType(),
        img = createBitmap(),      // img必须传
        title = "test",
        webUrl = URL.WEB_URL,     // 网页必传
        description = "测试",
        onSuccess = { type ->
//          toast("$type 分享成功")
        },
        onError = { type, code, msg ->
//          toast("$type:$code == $msg")
        },
        onCancel = { _ -> })
    }

    btn_wx_auth.setOnClickListener {
     com.yin.social.Social.auth(this, PlatformType.WEIXIN,
        onSuccess = { type, map ->
//          toast("$type 登录成功")
          map?.let {
            // 数据获取示例
            val data = CallbackDataUtil.getID(it, "")
          }
        },
        onError = { type, code, msg ->
//          toast("$type:$code == $msg")
        })
    }

    btn_wx_pay.setOnClickListener {
    com.yin.social.Social.pay(context = this,
        type = PlatformType.WEIXIN,
        appid = "wx650323cf15620a10",
        noncestr = "jmF8wF",
        partnerid = "1487126152",
        prepayid = "wx27103230843309aed1fc5c572788301867",
        timestamp = "1553653953",
        sign = "0F7393A34346E5B9AF0C8B6D708F4ADC",
        submitPayType = 0,
        onSuccess = {
//          toast("微信支付成功")
        },
        onError = { type, code, msg -> {
//          toast("$type:$code == $msg")
        }}
      )
    }
  }

  private fun createBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val paint = Paint().apply {
      color = Color.GREEN
      style = Paint.Style.FILL
      textSize = 100F
    }
    canvas.drawText("微信分享测试", 50f, 50f, paint)
    return bitmap
  }


  private fun getType(): PlatformType {
    return if (cb_share2_circle.isChecked) PlatformType.WEIXIN_CIRCLE else PlatformType.WEIXIN
  }
}
