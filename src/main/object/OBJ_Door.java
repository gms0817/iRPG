package main.object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
public class OBJ_Door extends SuperObject {
    public OBJ_Door() {
        name = "door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door01.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
