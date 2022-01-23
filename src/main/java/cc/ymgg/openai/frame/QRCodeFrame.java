package cc.ymgg.openai.frame;

import cc.ymgg.openai.constants.ConstantsForFrame;
import cc.ymgg.openai.qrcode.QRCodeUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class QRCodeFrame extends JFrame implements Runnable {
    private String content;
    public QRCodeFrame(int width,int height,String content){
        this.setBounds(ConstantsForFrame.DEFAULT_SCREEN_WIDTH.var/2-width/2,ConstantsForFrame.DEFAULT_SCREEN_HEIGHT.var/2-height/2,width,height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.content = content;
    }

    @Override
    public void run() {
        this.setVisible(true);
        refreshQRCode();
    }
    public void paint(Graphics graphics){
        try {
            BufferedImage QRCode = QRCodeUtils.generateQRCode(content,this.getWidth()+100,this.getHeight()+100);
            graphics.drawImage(QRCode,this.getWidth()/2-QRCode.getWidth()/2,this.getHeight()/2-QRCode.getHeight(),null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setContent(String content){
        this.content = content;
    }
    private void refreshQRCode(){
        JFrame frame = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.repaint();
            }
        });
    }
}
