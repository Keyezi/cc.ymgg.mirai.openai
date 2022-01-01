package cc.ymgg.openai.api;

import java.util.HashMap;

public interface TextArea {
    void setTextToArea(String text);
    String getTextFromArea();
    void appendTextToArea(String text);
    String getText();
}
