
import java.awt.*;
import java.util.Random;

public class Award{
    boolean eated=false;
    int X,Y;
    public Award(int x ,int y){ X=x; Y=y;}
    public void paint(Graphics g) {
        if (!eated){
        g.setColor(Color.GREEN);
        g.drawRect(X,Y,50,50);}
        else{
        X=-1; Y=-1;}
    }
}
