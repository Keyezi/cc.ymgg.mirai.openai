package cc.ymgg.openai.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object PluginConf: AutoSavePluginConfig("Config") {
    /**  回复@询问对象 */
    @ValueDescription("回复@询问者")
    var atreply by value<Boolean>()
    var replyat by value<Boolean>()
}