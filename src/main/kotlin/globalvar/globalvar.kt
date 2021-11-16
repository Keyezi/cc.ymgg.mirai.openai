package cc.ymgg.openai.globalvar

import cc.ymgg.openai.wechatapi.WechatAPI
import okhttp3.OkHttpClient

val httpClient = OkHttpClient()
var wechatAPI: WechatAPI = WechatAPI("", "")
var enable: Boolean = false