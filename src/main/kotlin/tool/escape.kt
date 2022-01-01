package cc.ymgg.openai.tool

import com.alibaba.fastjson.JSON
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import net.mamoe.mirai.message.data.MessageChain

object escape {
    fun WechatToMirai(text: String): MessageChain.Companion {
        
        return MessageChain
    }
    
    suspend fun MiraiToWechat(msgchain: MessageChain): String {
        
        var text = msgchain.contentToString()
        var imageLinkList = ArrayList<String>()
        for (msg in msgchain) when (msg) {
            is Image -> imageLinkList.add(msg.queryUrl())
        }
        for (image in imageLinkList){
            text.replaceFirst(oldValue = "[图片]", newValue = "\n$image\n")
        }
        var json = JSON.parse("{}")
        
        return ""
    }
}