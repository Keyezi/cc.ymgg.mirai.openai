package cc.ymgg.openai

import cc.ymgg.openai.config.PluginConf
import cc.ymgg.openai.config.WechatConf
import cc.ymgg.openai.globalvar.enable
import cc.ymgg.openai.globalvar.wechatAPI
import cc.ymgg.openai.logutil.Log
import cc.ymgg.openai.messagecenter.GroupMessageCenter
import cc.ymgg.openai.messagecenter.PrivateMessageCenter
import cc.ymgg.openai.wechatapi.WechatAPI
import com.alibaba.fastjson.JSON
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.message.data.At
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities


object PluginMain : KotlinPlugin(JvmPluginDescription(id = "cc.ymgg.openai", name = "AI闲聊", version = "2021.11")) {
    
    override fun onEnable() {
        // logger.info { "Plugin loaded" }
        PluginConf.reload()
        WechatConf.reload()
        
        wechatAPI = WechatAPI(WechatConf.TOKEN, WechatConf.EncodingAESKey)
        
        PluginConf.useGroupList = listOf(1213123L, 4564876L)
        this.globalEventChannel().subscribeAlways<GroupMessageEvent> {
            if (enable and PluginConf.enableGroupChat) GroupMessageCenter.run(this) }
        this.globalEventChannel().subscribeAlways<FriendMessageEvent> {
            if (enable and PluginConf.enablePrivateChat) PrivateMessageCenter.run(this) }
    }
}