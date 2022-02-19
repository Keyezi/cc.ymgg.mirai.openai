package cc.ymgg.openai.checker

import cc.ymgg.openai.config.PluginConf
import cc.ymgg.openai.logutil.Log
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.At

object Checker {
    val conf = PluginConf
    
    /**检查是否启用群*/
    fun groupEnableCheck(groupid: Long): Boolean {
        Log.v("群聊模式${conf.enableGroupChat}", "groupEnableCheck")
        if (!conf.enableGroupChat) return false
        
        
        for (group in conf.useGroupList) {
            Log.v("比较群$groupid,列表$group，模式${conf.enableGroupListMode}", "groupEnableCheck")
            if (groupid == group) return conf.enableGroupListMode
            if (group == conf.useGroupList[conf.useGroupList.size - 1]) return !conf.enableGroupListMode
        }
        Log.e("【成就1】不应该啊，你发现了盲点，请报告给开发人员", "groupEnableCheck")
        return false
    }
    
    /**检查是否使用前缀*/
    fun prefixUseCheck(text: String): Boolean {
        Log.v("前缀为:${conf.prefix}", "prefixUseCheck")
        if (conf.prefix.isBlank()) return true
        if (text.indexOf(conf.prefix) in 0..1) return true
        return false
    }
    
    /**检查是否AT*/
    fun atUseCheck(message: GroupMessageEvent): Boolean {
        if (!conf.replyat) return true
        for (msg in message.message) when (msg) {
            At(message.bot.id)                        -> {
                Log.v("检测到被AT", "atUseCheck")
                return true
            }
            
            message.message[message.message.size - 1] -> {
                Log.v("没检测到被AT", "atUseCheck")
                return false
            }
        }
        Log.e("【成就2】不应该啊，你发现了盲点，请报告给开发人员", "atUseCheck")
        return true
    }
    
    /**上限检查*/
    fun upperLimitCheck(): Boolean {
        Log.v("暂无完成上限功能。", "upperLimitCheck")
        return true
    }
    
    /**概率回复检查*/
    fun probabilityReply(): Boolean {
        Log.v("概率${conf.replyProbability}", "probabilityReply")
        return (1..100).random() <= conf.replyProbability
        
    }
}
