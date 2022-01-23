package cc.ymgg.openai.constants;

import java.awt.*;

public enum ConstantsForFrame {
    DEFAULT_SCREEN_WIDTH(Toolkit.getDefaultToolkit().getScreenSize().width),
    DEFAULT_SCREEN_HEIGHT(Toolkit.getDefaultToolkit().getScreenSize().height);
    public int var;
    ConstantsForFrame(int var){
        this.var = var;
    }
}
