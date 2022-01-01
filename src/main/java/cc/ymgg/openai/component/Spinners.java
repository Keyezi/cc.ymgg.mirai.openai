package cc.ymgg.openai.component;

import javax.swing.*;
import java.awt.*;

public class Spinners extends JSpinner {
    public Spinners(Container fatherContainer,int x,int y,int width,int height){
        fatherContainer.add(this);
        this.setBounds(x,y,width,height);
    }
}
