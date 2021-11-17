package cc.ymgg.openai;

import javax.swing.*;
import java.awt.*;
import cc.ymgg.openai.api.TextArea;

public class InputTextArea extends JTextArea implements TextArea {

    public InputTextArea(Container fatherContainer, int x, int y, int width, int height, boolean editable){
        fatherContainer.add(this);
        this.setBounds(x,y,width,height);
        this.setEditable(editable);
    }
    @Override
    public void setTextToArea(String text){
        this.setText(text);
    }

    @Override
    public String getTextFromArea(){
        return this.getText();
    }

    @Override
    public void appendTextToArea(String text){
        this.append(text);
    }
    public JTextArea getArea(){
        return this;
    }
}
