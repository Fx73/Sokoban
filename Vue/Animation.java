package Vue;

import java.awt.*;

public abstract class Animation {
    ImageBank imagebank;
    int animstep = 0;

    Animation(ImageBank _imagebank){
        imagebank = _imagebank;
    }

    Image GetAnimation(){
        return null;
    }

    void AvancerAnimation(){
        if(animstep == 0)
            animstep = 1;
        else
            animstep = 0;
    }
}
