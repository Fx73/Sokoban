package Vue;

import java.awt.*;

public abstract class Animation {
    ImageBank imagebank;
    Animation(ImageBank _imagebank){
        imagebank = _imagebank;
    }

    Image GetImage(){
        return null;
    }
}
