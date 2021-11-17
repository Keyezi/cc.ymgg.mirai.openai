package cc.ymgg.openai;

import java.awt.*;

public enum ConstantsForFrame {
    DEFAULT_SCREEN_HEIGHT(Toolkit.getDefaultToolkit().getScreenSize().height),
    DEFAULT_SCREEN_WIDTH(Toolkit.getDefaultToolkit().getScreenSize().width),
    DEFAULT_MAIN_FRAME_HEIGHT(DEFAULT_SCREEN_HEIGHT.var/2),
    DEFAULT_MAIN_FRAME_WIDTH(DEFAULT_SCREEN_WIDTH.var/2),
    DEFAULT_MAIN_FRAME_X_LOCATION(DEFAULT_SCREEN_WIDTH.var/2- DEFAULT_MAIN_FRAME_WIDTH.var/2),
    DEFAULT_MAIN_FRAME_Y_LOCATION(DEFAULT_SCREEN_HEIGHT.var/2-DEFAULT_MAIN_FRAME_HEIGHT.var/2);
    public int var;
    ConstantsForFrame(int var){
        this.var = var;
    }
}
