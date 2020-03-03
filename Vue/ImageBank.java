package Vue;

import java.awt.*;

public abstract class ImageBank {
    ImageBank(){
        Load();
    }
    Point coord = null;
    private void Load() {

    }

    public Image GetImage(int a){
        return null;
    }
    public void SetCoord(int x, int y){
        coord = new Point(x,y);
    }

}
