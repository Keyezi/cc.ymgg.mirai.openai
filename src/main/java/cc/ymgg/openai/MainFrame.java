package cc.ymgg.openai;

import javax.swing.*;

public class MainFrame implements Runnable{
    private final JFrame frame;
    private Panels interfaceInformationPanel;
    private String style = UIManager.getSystemLookAndFeelClassName();
    public MainFrame(){
        this.frame = new JFrame();
    }
    @Override
    public void run(){
        launchMainFrame();
        addPanelsToFrame();
        addPanelsToFrame();
    }
    public void launchMainFrame(){
        try{
            UIManager.setLookAndFeel(style);
        }catch(Exception e){
            e.printStackTrace();
        }
        frame.setBounds(ConstantsForFrame.DEFAULT_MAIN_FRAME_X_LOCATION.var, ConstantsForFrame.DEFAULT_MAIN_FRAME_Y_LOCATION.var,ConstantsForFrame.DEFAULT_MAIN_FRAME_WIDTH.var,ConstantsForFrame.DEFAULT_MAIN_FRAME_HEIGHT.var);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    void addPanelsToFrame(){
        interfaceInformationPanel = new Panels(frame,4,27,588,114);

    }
    void addComponentToInterfacePanel(){
        InputTextArea[] inputTextAreas = new InputTextArea[3];
        JLabel[] labels = new JLabel[3];
        for (int i =1;i<inputTextAreas.length+1;i++){
            inputTextAreas[i-1] = new InputTextArea(interfaceInformationPanel,113,i*27,295,24,true);
            labels[i-1] = new JLabel();
            labels[i-1].setHorizontalAlignment(JLabel.RIGHT);
            labels[i-1].setBounds(8,i*27,100,24);
        }
        labels[0].setText("Appid");
        labels[1].setText("TOKEN");
        labels[2].setText("EcodingAESKey");
    }
}
