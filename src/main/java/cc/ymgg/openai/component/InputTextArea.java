package cc.ymgg.openai.component;

import javax.swing.*;
import java.awt.*;
import cc.ymgg.openai.api.TextArea;

public class InputTextArea extends JTextArea implements TextArea {
    public InputTextArea(){

    }
    public InputTextArea(Container fatherContainer, int x, int y, int width, int height, boolean editable){
        fatherContainer.add(this);
        this.setBounds(x,y,width,height);
        this.setEditable(editable);
        this.setName("displayTextArea");
        this.setBackground((new Color(240,240,240)));
    }
    public InputTextArea(Container fatherContainer, int x, int y, int width, int height,boolean editable,String name){
        fatherContainer.add(this);
        this.setBounds(x,y,width,height);
        this.setEditable(editable);
        this.setBorder(BorderFactory.createTitledBorder(name));
        this.setName(name);
        this.setBackground((new Color(240,240,240)));
    }
    public InputTextArea(Container fatherContainer, int x, int y, int width, int height,String name){
        fatherContainer.add(this);
        this.setBounds(x,y,width,height);
        this.setName(name);
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
}
