import cc.ymgg.openai.MainFrame;

public class testForFrame {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        Thread thread = new Thread(mainFrame);
        thread.start();
    }
}
