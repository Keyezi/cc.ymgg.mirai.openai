package cc.ymgg.openai.component;

import cc.ymgg.openai.api.Panel;
import cc.ymgg.openai.api.TextArea;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Panels extends JPanel implements Panel {
    public Panels(int x,int y,int width,int height){
        this.setBounds(x,y,width,height);
    }
    public Panels(Container fatherContainer){
        fatherContainer.add(this);
    }
    public Panels(Container fatherContainer,int x,int y,int width,int height){
        fatherContainer.add(this);
        this.setBounds(x,y,width,height);
    }
    @Override
    public void addComponentToPanel(Component component){
        this.add(component);
    }
    public HashMap<String,String> getStringDataFromPanel(){
        HashMap<String,String> StrData = new HashMap<>();
        Component[] components = this.getComponents();
        for (Component component : components) {
            TextArea area = component instanceof TextArea ? ((TextArea) component) : null;
            if (area!=null){
                StrData.put(component.getName(),area.getText());
            }
        }
        return StrData;
    }
}
