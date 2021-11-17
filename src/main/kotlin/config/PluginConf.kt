package cc.ymgg.openai.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object PluginConf: AutoSavePluginConfig("Config") {
    /**  回复@询问对象 */
    @ValueDescription("回复时@询问者")
    var atreply by value<Boolean>()
    /**  被@时回复 */
    @ValueDescription("被@回复")
    var replyat by value<Boolean>()
    
    var useGroupList by value<List<Long>>()
    var enableGroupListMode by value<Boolean>()
    var enableGroupChat by value<Boolean>()
    var enablePrivateChat by value<Boolean>()
    var prefix by value<String>()
    
    
}