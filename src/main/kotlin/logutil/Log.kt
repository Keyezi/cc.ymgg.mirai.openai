package cc.ymgg.openai.logutil

import cc.ymgg.openai.PluginMain

@Suppress("unused")
object Log {
    
        private const val VERBOSE = 1
        private const val DEBUG = 2
        private const val INFO = 3
        private const val WARN = 4
        private const val ERROR = 5
        
        
        private var level = VERBOSE
        
        private val MiraiLog = PluginMain.logger
        fun v(msg: String, title: String? = null) {
            if (level <= VERBOSE) MiraiLog.verbose(cw(msg, title))
        }
        
        fun d(msg: String, title: String? = null) {
            if (level <= DEBUG) MiraiLog.debug(cw(msg, title))
        }
        
        fun i(msg: String, title: String? = null) {
            if (level <= INFO) MiraiLog.info(cw(msg, title))
        }
        
        fun w(msg: String, title: String? = null) {
            if (level <= WARN) MiraiLog.warning(cw(msg, title))
        }
        
        fun e(msg: String, title: String? = null) {
            if (level <= ERROR) MiraiLog.error(cw(msg, title))
        }
        
        private fun cw(msg: String, title: String?): String = if (title == null) msg else "[$title]$msg"
    
    
}

