package Vue;

import java.awt.*;

public abstract class ImageBank {
    ImageBank(){
        Load();
    }
    Point coord = null;
     abstract void Load();

    public Image GetImage(int a){
        return null;
    }
    public void SetCoord(int x, int y){
        coord = new Point(x,y);
    }

}
