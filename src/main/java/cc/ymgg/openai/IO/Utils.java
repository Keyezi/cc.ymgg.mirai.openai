package cc.ymgg.openai.IO;


import javax.imageio.ImageIO;
import java.awt.*;


public class Utils {
    public static Thread threadFactory(Runnable runnable){
        return  new Thread(runnable);
    }
    public static Image getImageFromFile(String resourceName){
        Image image = null;
        try {
            image = ImageIO.read(Utils.class.getClassLoader().getResourceAsStream(resourceName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return image;
    }
}
