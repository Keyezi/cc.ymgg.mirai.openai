package cc.ymgg.openai.data

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object GroupMessageData : AutoSavePluginData("GroupMessageData") {
    var messageCount by value<Map<Long, Long>>()
}