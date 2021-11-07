package cc.ymgg.openai.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

object WechatConf : AutoSavePluginConfig("WeChat") {
    var APPID:String by value()
    var TOKEN:String by value()
    var EncodingAESKey:String by value()
}