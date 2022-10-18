package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_SpeedBoots extends SuperObject{
    public OBJ_SpeedBoots() {
        name = "speedBoots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/speedBoots.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
