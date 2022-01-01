package cc.ymgg.openai.component;

import javax.swing.*;
import java.awt.*;

public class CheckBoxes extends JCheckBox {
    public CheckBoxes(Container fatherContainer,int x,int y,int width,int height){
        fatherContainer.add(this);
        this.setBounds(x,y,width,height);
    }
}
