package cc.ymgg.openai.frame;

import cc.ymgg.openai.ApplicationTest;
import cc.ymgg.openai.IO.Utils;
import cc.ymgg.openai.component.*;
import cc.ymgg.openai.constants.ComponentNames;
import cc.ymgg.openai.constants.ConstantsForFrame;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

public class MainFrame implements Runnable{
    private final JFrame frame;
    private final CardPanels cardPanels;
    private boolean stratWithFrameLauch;
    public MainFrame(boolean parameter){
        this();
        this.stratWithFrameLauch = parameter;
    }
    public MainFrame(){
        try{
            String style = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(style);
        }catch(Exception e){
            e.printStackTrace();
        }
        this.frame = new JFrame();
        cardPanels = new CardPanels(frame,0,0,frame.getWidth(),10);
    }
    @Override
    public void run(){
        if (stratWithFrameLauch){
            launchMainFrame();
            setFont();
        }
        addTray();
    }
    public void launchMainFrame(){
        frame.setBounds(ConstantsForFrame.DEFAULT_MAIN_FRAME_X_LOCATION.var, ConstantsForFrame.DEFAULT_MAIN_FRAME_Y_LOCATION.var,640,590);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cardPanels.setBounds(0,0,frame.getWidth()-10,frame.getHeight()/20*18);
        addBasicSettingCardToFrame();
        addAdvanceFunctionCardToFrame();
    }
    void addBasicSettingCardToFrame(){
        Panels interfaceInfoPanel = new Panels(5,5,frame.getWidth()-40,frame.getHeight()/4);
        interfaceInfoPanel.setBorder(BorderFactory.createTitledBorder("接口信息"));
        interfaceInfoPanel.setLayout(null);
        InputTextArea[] inputTextAreas = new InputTextArea[ComponentNames.INTERFACE_PANEL_CMP_NAMES.length];
        for (int i = 0; i < inputTextAreas.length; i++) {
            inputTextAreas[i] = new InputTextArea(interfaceInfoPanel,interfaceInfoPanel.getWidth()/20,(i*interfaceInfoPanel.getHeight()/4+2)+interfaceInfoPanel.getHeight()/6-3,interfaceInfoPanel.getWidth()/10*7,interfaceInfoPanel.getHeight()/4+3,true, ComponentNames.INTERFACE_PANEL_CMP_NAMES[i]);
        }
        InputTextArea notice = new InputTextArea(interfaceInfoPanel,inputTextAreas[0].getX()+inputTextAreas[0].getWidth(),inputTextAreas[0].getY(),inputTextAreas[0].getWidth()/3,inputTextAreas[0].getHeight()*2,false);
        notice.setTextToArea("不是小程序或插件的Appid,请勿填错");
        notice.setLineWrap(true);
        notice.setFont(new Font(notice.getFont().getName(), Font.PLAIN,10)); notice.setForeground(new Color(171, 173, 179));
        Panels prefixSettingPanel = new Panels(5,interfaceInfoPanel.getHeight()+5,frame.getWidth()/2-20,interfaceInfoPanel.getHeight());
        prefixSettingPanel.setBorder(BorderFactory.createTitledBorder("前缀设置"));
        prefixSettingPanel.setLayout(null);
        InputTextArea prefixSetting = new InputTextArea(prefixSettingPanel,5,15,prefixSettingPanel.getWidth()/2,39,true, ComponentNames.PREFIX_SETTING_TEXT_AREA_NAME);
        CheckBoxes[] prefixSettingsCheckBoxes = new CheckBoxes[ComponentNames.PREFIX_SETTING_CHECK_BOXES_NAMES.length];
        for (int i=0;i<prefixSettingsCheckBoxes.length;i++){
            prefixSettingsCheckBoxes[i] = new CheckBoxes(prefixSettingPanel,prefixSetting.getX(),prefixSetting.getY()+(i*30)+35,prefixSetting.getWidth()+20,prefixSetting.getHeight());
            prefixSettingsCheckBoxes[i].setText(ComponentNames.PREFIX_SETTING_CHECK_BOXES_NAMES[i]);
            prefixSettingsCheckBoxes[i].setName(ComponentNames.PREFIX_SETTING_CHECK_BOXES_NAMES[i]);
        }
        Panels replySettings = new Panels(prefixSettingPanel.getWidth()+5,interfaceInfoPanel.getHeight()+5,frame.getWidth()/2-20,interfaceInfoPanel.getHeight());
        replySettings.setLayout(null);
        CheckBoxes[] replySettingsCheckBoxes = new CheckBoxes[ComponentNames.REPLY_SETTING_CHECK_BOXES_NAMES.length];
        for(int i =0;i<replySettingsCheckBoxes.length-1;i++){
            replySettingsCheckBoxes[i] = new CheckBoxes(replySettings,(i*100)+5,20,100,20);
            replySettingsCheckBoxes[i].setText(ComponentNames.REPLY_SETTING_CHECK_BOXES_NAMES[i]);
            replySettingsCheckBoxes[i].setName(ComponentNames.REPLY_SETTING_CHECK_BOXES_NAMES[i]);
        }
        Spinners spinners = new Spinners(replySettings,75,replySettingsCheckBoxes[0].getY()+replySettingsCheckBoxes[0].getHeight()+5,replySettings.getWidth()/3,replySettings.getHeight()/6);
        spinners.setToolTipText("范围[0-100],100为全部回复");
        InputTextArea noticeForReply = new InputTextArea(replySettings,5,spinners.getY(),60,spinners.getHeight(),false);
        noticeForReply.setTextToArea("回复概率");
        replySettings.setBorder(BorderFactory.createTitledBorder("回复设置"));
        CheckBoxes replyCheck = new CheckBoxes(replySettings,noticeForReply.getX(),spinners.getY()+spinners.getHeight()+3,spinners.getWidth()+noticeForReply.getWidth(),spinners.getHeight());
        replyCheck.setText(ComponentNames.REPLY_SETTING_CHECK_BOXES_NAMES[2]);
        replyCheck.setName(ComponentNames.REPLY_SETTING_CHECK_BOXES_NAMES[2]);
        Panels otherSettingsPanel = new Panels(prefixSettingPanel.getX(),prefixSettingPanel.getY()+prefixSettingPanel.getHeight()+5,frame.getWidth()/10*5,prefixSettingPanel.getHeight());
        otherSettingsPanel.setLayout(null);
        otherSettingsPanel.setBorder(BorderFactory.createTitledBorder("其他设置"));
        CheckBoxes[] otherSettingsCheckBoxes = new CheckBoxes[ComponentNames.OTHER_SETTING_CHECK_BOXES_NAMES.length];
        for (int i = 0; i < otherSettingsCheckBoxes.length; i++) {
            otherSettingsCheckBoxes[i] = new CheckBoxes(otherSettingsPanel,5,(i*50)+15,otherSettingsPanel.getWidth()/2,otherSettingsPanel.getHeight()/6);
            otherSettingsCheckBoxes[i].setText(ComponentNames.OTHER_SETTING_CHECK_BOXES_NAMES[i]);
            otherSettingsCheckBoxes[i].setName(ComponentNames.OTHER_SETTING_CHECK_BOXES_NAMES[i]);
        }
        InputTextArea[] noticeForOtherSettings = new InputTextArea[ComponentNames.OTHER_SETTING_TEXT_AREA_CONTENT.length];
        for (int i = 0; i < noticeForOtherSettings.length; i++) {
            noticeForOtherSettings[i] = new InputTextArea(otherSettingsPanel,otherSettingsCheckBoxes[0].getX(),otherSettingsCheckBoxes[i].getY()+otherSettingsCheckBoxes[i].getHeight()+3,otherSettingsCheckBoxes[i].getWidth(),43,false);
            noticeForOtherSettings[i].setLineWrap(true);
            noticeForOtherSettings[i].setTextToArea(ComponentNames.OTHER_SETTING_TEXT_AREA_CONTENT[i]);
            noticeForOtherSettings[i].setFont(new Font(noticeForOtherSettings[i].getFont().getName(), Font.PLAIN,10));
            noticeForOtherSettings[i].setForeground(new Color(171, 173, 179));
        }
        Panels splitGroupPanel = new Panels(otherSettingsPanel.getX()+otherSettingsPanel.getWidth()+5,otherSettingsPanel.getY(),frame.getWidth()/10*5-30,otherSettingsPanel.getHeight());
        splitGroupPanel.setLayout(null);
        splitGroupPanel.setBorder(BorderFactory.createTitledBorder("分群"));
        InputTextArea splitGroupTextArea = new InputTextArea();
        splitGroupTextArea.setName("splitGroupTextArea");
        JScrollPane splitGroupTextAreaScroll = new JScrollPane(splitGroupTextArea);
        splitGroupPanel.add(splitGroupTextAreaScroll);
        splitGroupTextAreaScroll.setBounds(10,20,splitGroupPanel.getWidth()-20,splitGroupPanel.getHeight()/6*4);
        ButtonGroup group = new ButtonGroup();
        RadioButtons[] radioButtons = new RadioButtons[ComponentNames.SPLIT_GROUP_RADIO_BUTTON_NAMES.length];
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i] = new RadioButtons(splitGroupPanel,(i*splitGroupPanel.getWidth()/4)+10,splitGroupTextAreaScroll.getY()+splitGroupTextAreaScroll.getHeight(),splitGroupPanel.getWidth()/4,splitGroupPanel.getHeight()/6);
            radioButtons[i].setText(ComponentNames.SPLIT_GROUP_RADIO_BUTTON_NAMES[i]);
            group.add(radioButtons[i]);
        }
        radioButtons[1].setSelected(true);
        InputTextArea noticeForSplitGroup = new InputTextArea(splitGroupPanel,radioButtons[1].getX()+radioButtons[1].getWidth(),radioButtons[1].getY()+2,splitGroupPanel.getWidth()/2-20,radioButtons[1].getHeight(),false);
        noticeForSplitGroup.setFont(new Font(noticeForSplitGroup.getFont().getName(), Font.PLAIN,10));
        noticeForSplitGroup.setForeground(new Color(171, 173, 179));
        noticeForSplitGroup.setText("不填不会启用,使用"+'"'+"|"+'"'+"分割");
        cardPanels.registerPanel("基础设置",interfaceInfoPanel,prefixSettingPanel,replySettings,otherSettingsPanel,splitGroupPanel);
    }
    void addAdvanceFunctionCardToFrame(){
        Panels advanceSettingsPanel = new Panels(0,0,frame.getWidth(),frame.getHeight());
        advanceSettingsPanel.setLayout(null);
        CheckBoxes[] advanceSettingsCheckBoxes = new CheckBoxes[ComponentNames.ADVANCE_SETTING_CHECK_BOXES_NAMES.length];
        for (int i = 0; i < advanceSettingsCheckBoxes.length; i++) {
            advanceSettingsCheckBoxes[i] = new CheckBoxes(advanceSettingsPanel,10,(i*30)+20,advanceSettingsPanel.getWidth()/3-40,35);
            advanceSettingsCheckBoxes[i].setText(ComponentNames.ADVANCE_SETTING_CHECK_BOXES_NAMES[i]);
        }
        advanceSettingsCheckBoxes[0].setToolTipText("平台内没有触发任何关键词就不回复");
        new InputTextArea(advanceSettingsPanel,advanceSettingsCheckBoxes[1].getX()+advanceSettingsCheckBoxes[1].getWidth(),advanceSettingsCheckBoxes[1].getY(),advanceSettingsPanel.getWidth()/5,20,"groupCalloutLimit").setBorder(BorderFactory.createLineBorder(Color.gray));
        new InputTextArea(advanceSettingsPanel,advanceSettingsCheckBoxes[2].getX()+advanceSettingsCheckBoxes[2].getWidth(),advanceSettingsCheckBoxes[2].getY(),advanceSettingsPanel.getWidth()/5,20,"singleAccountCalloutLimit").setBorder(BorderFactory.createLineBorder(Color.gray));
        Spinners delaySpinner = new Spinners(advanceSettingsPanel,advanceSettingsCheckBoxes[3].getX()+advanceSettingsCheckBoxes[3].getWidth()+5,advanceSettingsCheckBoxes[3].getY(),advanceSettingsPanel.getWidth()/5,25);
        delaySpinner.setToolTipText("1000ms=1s");
        cardPanels.registerPanel("高级设置",advanceSettingsPanel);
    }
    void setFont(){
        FontUIResource fontRes  =   new  FontUIResource(ApplicationTest.getFontFromFile.getFont());
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();){
            Object key  =  keys.nextElement();
            Object value  =  UIManager. get (key);
            if (value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }
}
