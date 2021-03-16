

import java.awt.*;

public class Packman {
    int X,Y;
    static int Score;
    Packman(int x,int y){X=x; Y=y;}
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawOval(X,Y,100,100);
    }
}
