package cc.ymgg.openai;

import cc.ymgg.openai.IO.GetFontFromFile;
import cc.ymgg.openai.IO.Utils;
import cc.ymgg.openai.frame.MainFrame;

public class ApplicationTest {
    public static GetFontFromFile getFontFromFile = new GetFontFromFile(9);
    public static void main(String[] args) {
        Utils.threadFactory(getFontFromFile).start();
        Thread thread = new Thread(new MainFrame(true));
        thread.start();
    }
}
