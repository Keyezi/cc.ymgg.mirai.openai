package cc.ymgg.openai.messagecenter

import cc.ymgg.openai.checker.Checker
import cc.ymgg.openai.config.PluginConf
import cc.ymgg.openai.config.PluginConf.replyat
import cc.ymgg.openai.config.WechatConf
import cc.ymgg.openai.globalvar.wechatAPI
import cc.ymgg.openai.logutil.Log
import cc.ymgg.openai.tool.Escape
import com.alibaba.fastjson.JSON
import kotlinx.coroutines.delay
import net.mamoe.mirai.console.command.ConsoleCommandSender.bot
import net.mamoe.mirai.contact.ContactOrBot
import net.mamoe.mirai.contact.User
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.content
import java.lang.Thread.sleep

object GroupMessageCenter {
    suspend fun run(msg: GroupMessageEvent) {
        // if ((msg.message[1] != At(msg.bot.id)) and PluginConf.atreply) return //废弃方法
        Log.i("接收到消息", "群消息中心")
        
        if (!Checker.atUseCheck(msg)) return
        
        if (!Checker.probabilityReply()) return
/*        if ((1..100).random() > PluginConf.replyProbability) {
            Log.v("概率回复不通过", "群消息中心-概率回复")
            return
        } else {
            Log.v("概率回复通过", "群消息中心-概率回复")
        }*/
        if (!Checker.groupEnableCheck(msg.group.id)) return
        if (!Checker.prefixUseCheck(msg.message.contentToString())) return
        if (!Checker.upperLimitCheck()) return
        val result =
            wechatAPI.doRequest(msg.sender.id, msg.sender.nick, Escape.miraiToWechat(msg.message), WechatConf.debug)
        
        val json = JSON.parseObject(result)
        val backAnswer = json.getString("answer")
        Log.v(result, "群消息中心-json内容")
        if (backAnswer.isNotBlank()) {
            delay(PluginConf.delayedReply)
            msg.group.sendMessage(backAnswer)
        } else {
            Log.v("answer为空，不发送消息", "群消息中心-空不发送")
        }
        
        
    }
}