package cc.ymgg.openai

import cc.ymgg.openai.config.PluginConf
import cc.ymgg.openai.config.WechatConf
import cc.ymgg.openai.logutil.Log
import cc.ymgg.openai.wechatapi.WechatAPI
import com.alibaba.fastjson.JSON
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.globalEventChannel


object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "cc.ymgg.openai",
        name = "AI闲聊",
        version = "2021.6"
                        )
                                ) {
    
    override fun onEnable() {
        // logger.info { "Plugin loaded" }
        PluginConf.reload()
        WechatConf.reload()
/*        val qid = 10000L
        val name = "Mar"
        val msg = "你好"

        val wechatAPI = WechatAPI("v21QrJMiyHtPRFZNgwwG4LrLQkWbRX", "QDJQHttWQQ5Rd0crTrWeuC1vH6S6nHbeDJfHK1otSUA")
        val result = wechatAPI.doRequest(qid, name, msg)
        val json = JSON.parseObject(result)
        Log.i(json.getString("answer"))*/
        
        //val wechatAPI = WechatAPI("v21QrJMiyHtPRFZNgwwG4LrLQkWbRX","QDJQHttWQQ5Rd0crTrWeuC1vH6S6nHbeDJfHK1otSUA")
        this.globalEventChannel().subscribeAlways<GroupMessageEvent> {
            //val GROUP: Long = group.id
            val wechatAPI = WechatAPI(WechatConf.TOKEN, WechatConf.EncodingAESKey)
            val result = wechatAPI.doRequest(sender.id, sender.nick, this.message.toString())
            val json = JSON.parseObject(result)
            Log.i(result)
            group.sendMessage(json.getString("answer"))
            
        }
        
        
    }
    
}