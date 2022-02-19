package cc.ymgg.openai.tool

import cc.ymgg.openai.logutil.Log
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.MessageChainBuilder

object Escape {
    fun WechatToMirai(text: String): MessageChain {
        val builder = MessageChainBuilder()
        
        return builder.build()
    }
    
    suspend fun miraiToWechat(msgchain: MessageChain): String {
        val arrayJson = JSON.parseObject("{}")
        val jsonArray = JSONArray()
   
        for (msg in msgchain) when {
            msg.contentToString().isBlank() -> {
                Log.v("检测到空白文本，跳过检测。", "miraiToWechat")
                continue
            }
            
            msg is Image                    -> {
                Log.v("检测到图片内容消息，图片链接为:${msg.queryUrl()}", "miraiToWechat")
                val messageJson = JSON.parseObject("{}")
                val imageJson = JSON.parseObject("{}")
                imageJson["url"] = msg.queryUrl()
                messageJson["image"] = imageJson
                jsonArray.add(messageJson.toString())
            }
            
            else                            -> {
                Log.v("检测到文本消息，内容为:${msg.contentToString()}", "miraiToWechat")
                jsonArray.add(msg.contentToString())
            }
        }
        
        arrayJson["multimsg"] = jsonArray
        
        Log.i(arrayJson.toJSONString(), "miraiToWechat-输出json内容")
        
        
        return arrayJson.toJSONString()
    }
}