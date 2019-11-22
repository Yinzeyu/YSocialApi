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
import kotlinx.android.synthetic.main.activity_qq.*

class QQActivity : AppCompatActivity() {

  companion object {
    fun startActivity(context: Context) {
      val intent = Intent(context, QQActivity::class.java)
      intent.putExtra("className", context::class.java.simpleName)
      context.startActivity(intent)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_qq)
    initEvent()
  }

  private fun initEvent() {
    btn_qq_auth.setOnClickListener {
      com.yin.social.Social.auth(this, PlatformType.QQ,
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

    btn_qq_share_img.setOnClickListener {
     com.yin.social.Social.share(this, getType(),
          img = createBitmap(),                  // 除了分享文本外，其他分享img都必须传
          onSuccess = { type ->
//            toast("$type 分享成功")
          },
          onError = { type, code, msg ->
//            toast("$type:$code == $msg")
          },
          onCancel = {_ -> })
    }
    btn_qq_share_music.setOnClickListener {
      com.yin.social.Social.share(this, getType(),
          img = createBitmap(),                  // img必须传
          title = "test",
          musicUrl = URL.AUDIO_URL, // 必须传
          musicDataUrl = "",   // 如果有需要传
          description = "测试",
          onSuccess = { type ->
//            toast("$type 分享成功")
          },
          onError = { type, code, msg ->
//            toast("$type:$code == $msg")
          },
          onCancel = {_ -> })
    }
    btn_qq_share_video.setOnClickListener {
     com.yin.social.Social.share(this, getType(),
          img = createBitmap(),                  // img必须传
          title = "test",
          videoUrl = URL.VIDEO_URL, // 必须传
          videoLowBandUrl = URL.VIDEO_URL,   // 如果分享平台是微信，可以传videoUrl或者videoLowBandUrl其一
          description = "测试",
          onSuccess = { type ->
//            toast("$type 分享成功")
          },
          onError = { type, code, msg ->
//            toast("$type:$code == $msg")
          },
          onCancel = {_ -> })
    }
    btn_qq_share_text.setOnClickListener {
     com.yin.social.Social.share(this, getType(),
          text = "测试",   // 必传
          onSuccess = { type ->
//            toast("$type 分享成功")
          },
          onError = { type, code, msg ->
//            toast("$type:$code == $msg")
          },
          onCancel = {_ -> })
    }
    btn_qq_share_web.setOnClickListener {
     com.yin.social.Social.share(this, getType(),
          img = createBitmap(),      // img必须传
          title = "test",
          webUrl = URL.WEB_URL,     // 网页必传
          description = "测试",
          onSuccess = { type ->
//            toast("$type 分享成功")
          },
          onError = { type, code, msg ->
//            toast("$type:$code == $msg")
          },
          onCancel = {_ -> })
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
    canvas.drawText("qq分享测试", 100f, 200f, paint)
    return bitmap
  }

  private fun getType(): PlatformType{
    return if(btn_qq_share2zone.isChecked) PlatformType.QQ_ZONE else PlatformType.QQ
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
   com.yin.social.Social.onActivityResult(requestCode, resultCode, data)
  }
}
