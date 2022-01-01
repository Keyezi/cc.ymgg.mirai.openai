package cc.ymgg.openai.IO;

import java.awt.*;
import java.io.File;
import java.net.URL;

public class GetFontFromFile implements Runnable {
    private Font font;
    private int fontSize;
    public GetFontFromFile(int fontSize){
        this.fontSize = fontSize;
    }
    @Override
    public void run() {
        Font createdFont = null;
        try {
            URL url = Utils.class.getClassLoader().getResource("msyh.ttc");
            assert url != null;
            File fontFile = new File(url.getFile());
            createdFont = Font.createFont(Font.TRUETYPE_FONT,fontFile);
            createdFont = createdFont.deriveFont(Font.PLAIN,fontSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.font = createdFont;
    }

    public Font getFont() {
        return font;
    }
}
