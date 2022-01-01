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
    @ValueDescription("排除/启用群列表")
    var useGroupList by value<List<Long>>()
    @ValueDescription("仅启用/停用GroupList中的群")
    var enableGroupListMode by value<Boolean>()
    @ValueDescription("启用群聊")
    var enableGroupChat by value<Boolean>()
    @ValueDescription("启用私聊")
    var enablePrivateChat by value<Boolean>()
    @ValueDescription("前缀")
    var prefix by value<String>()
}