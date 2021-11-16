package cc.ymgg.openai.messagecenter

import cc.ymgg.openai.config.PluginConf
import cc.ymgg.openai.config.PluginConf.prefix
import cc.ymgg.openai.globalvar.wechatAPI
import cc.ymgg.openai.logutil.Log
import com.alibaba.fastjson.JSON
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.MessageChain

object GroupMessageCenter {
    suspend fun run(msg: GroupMessageEvent) {
        // if ((msg.message[1] != At(msg.bot.id)) and PluginConf.atreply) return //废弃方法
        
        
        for (gp in PluginConf.useGroupList) {
            if (PluginConf.enableGroupListMode) {
                if (gp != msg.group.id) return
            } else {
                if (gp == msg.group.id) return
            }
            
        }
        
        
        val result = wechatAPI.doRequest(msg.sender.id, msg.sender.nick, msg.message.toString())
        
        val json = JSON.parseObject(result)
        
        Log.i(result)
        msg.group.sendMessage(json.getString("answer"))
        
        
    }
}