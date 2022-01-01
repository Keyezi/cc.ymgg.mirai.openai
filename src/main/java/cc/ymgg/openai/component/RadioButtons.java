package cc.ymgg.openai.component;

import javax.swing.*;
import java.awt.*;

public class RadioButtons extends JRadioButton{
    public RadioButtons(Container fatherContainer,int x,int y,int width,int height){
        fatherContainer.add(this);
        this.setBounds(x,y,width,height);
    }
}
