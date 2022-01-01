package cc.ymgg.openai.component;

import javax.swing.*;
import java.awt.*;

public class CardPanels extends JTabbedPane {
    public CardPanels(Container fatherContainer,int x,int y,int width,int height){
        fatherContainer.add(this);
        this.setBounds(x,y,width,height);
    }
    public CardPanels(){

    }
    public void registerPanel(String name,Panels... childPanel){
        Panels fatherPanel = new Panels(this);
        this.add(name,fatherPanel);
        fatherPanel.setLayout(null);
        for (Panels panels : childPanel) {
            fatherPanel.add(panels);
        }
    }
}
