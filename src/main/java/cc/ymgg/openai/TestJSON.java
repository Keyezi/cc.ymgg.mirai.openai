package cc.ymgg.openai;

import cc.ymgg.openai.logutil.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.SingleMessage;
import org.jetbrains.annotations.NotNull;

public class TestJSON {
    public static String testJSON(@NotNull MessageChain chain){
        JSONObject imageJSON = JSON.parseObject("{}");//新建一个用于存储Image内容的JSON session
        JSONObject messageJSON = JSON.parseObject("{}");//新建存储message 内容的JSON session(Image session的父成分)
        JSONArray array = new JSONArray();//新建JSON Session Array用于存储所有信息
        JSONObject parentJSON = JSON.parseObject("{}");//最后创建父JSON session
        for (SingleMessage singleMessage : chain) {
            if (singleMessage.contentToString().isBlank()){
                //如果消息为空，跳过处理
                continue;
            }
            if (singleMessage instanceof Image){
                Image image = (Image) singleMessage;
                Log.INSTANCE.d(Image.queryUrl(image),null);
            }
            if (singleMessage instanceof Image) {
                //如果消息为Image图像，获取其URL加入Image Session，然后将Image Session加入 message JSON
                Image image = (Image) singleMessage;
                String imageURL = Image.queryUrl(image);
                imageJSON.put("url",imageURL);
                messageJSON.put("image",imageJSON);
                array.add(messageJSON);
                parentJSON.put("multimsg",array);
                imageJSON = JSON.parseObject("{}");
                messageJSON = JSON.parseObject("{}");
                //最后将所有子Session 加入一个JSON Array，最后加入父Session
            }else {
                array.add(singleMessage.contentToString());
                parentJSON.put("multimsg",array);
                //如果消息不为Image图像，则直接将其以字符串形式加入父Session
            }
            Log.INSTANCE.i(parentJSON.toJSONString(),null);
        }
        //最后返回序列化好的父Session的JSON格式
        return parentJSON.toJSONString();
    }
}
