package cc.ymgg.openai.wechatapi

import cc.ymgg.openai.http.PostUtil
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACSigner
import java.time.ZoneId
import java.time.ZonedDateTime

class WechatAPI(InputToken: String, InputEncodingAESKey: String) {
    private lateinit var token: String
    private lateinit var jwtkey: String
    
    init {
        resetKey(InputToken, InputEncodingAESKey)
    }
    
    private fun resetKey(InputToken: String, InputEncodingAESKey: String): Boolean {
        token = InputToken
        jwtkey = InputEncodingAESKey
        return true
    }
    
    fun doRequest(
        qid: Long,
        name: String,
        msg: String,
        online: Boolean = true
                 ): String {
        
        val avatar = "http://q1.qlogo.cn/g?b=qq&nk=$qid&s=640"
        
        val singer = HMACSigner.newSHA256Signer(jwtkey)
        val jwt = JWT()
            .setIssuedAt(ZonedDateTime.now(ZoneId.of("Asia/Shanghai")))
            .setExpiration(ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).plusMinutes(60))
            .addClaim("userid", qid)
            .addClaim("avatar", avatar)
            .addClaim("username", name)
      // Log.e(ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).plusMinutes(60).toLocalTime().toString())
        val env = if (online) "online" else "debug"
        return PostUtil().build {
            url("https://openai.weixin.qq.com/openapi/aibot/$token")
            add("signature", JWT.getEncoder().encode(jwt, singer))
            add("query", msg)
            add("env", env)
        }
    }
    
}