package cc.ymgg.openai;

import cc.ymgg.openai.api.Panel;

import javax.swing.*;
import java.awt.*;

public class Panels extends JPanel implements Panel {
    public Panels(Container fatherContainer,int x,int y,int width,int height){
        fatherContainer.add(this);
        this.setBounds(x,y,width,height);
    }
    @Override
    public void addComponentToPanel(Component component){
        this.add(component);
    }
}
