

import java.awt.*;

public class GhostXaxis extends GameScreen implements Runnable{
    int velocity,X,Y,ID;
    boolean isItTurn = false, movable;
    boolean[] movability={false,false};
    GhostXaxis(int x,int y,int speed,int id){
        X=x; Y=y; velocity=speed; ID=id;
    }
    @Override
    public synchronized void run() {
        while (true) { movable=true; movability= new boolean[]{false, false};
            try {
                if (velocity == 3)
                    Thread.sleep(1000);
                else if (velocity == 2)
                    Thread.sleep(2000);
                else if (velocity == 1)
                    Thread.sleep(4000);

                for (int i = 0; i <positionsX.size() ; i++)
                if(i!=ID){
                    if ((X+150==positionsX.get(i) || (X+100==positionsX.get(i)))&& (positionsY.get(i)==Y)){isItTurn=true; movability[0]=true;}
                    else{
                    if ((X+150==positionsX.get(i) || (X+100==positionsX.get(i)))&& (positionsY.get(i)>=Y-50 && positionsY.get(i)<=Y+150)){isItTurn=true; movability[0]=true;}
                    if (i<5 &&(X+150==positionsX.get(i) || (X+100==positionsX.get(i)))&& (positionsY.get(i)==Y-100 && !gY[i].isItTurn)){isItTurn=true; movability[0]=true;}
                    if (i<5 &&(X+150==positionsX.get(i) || (X+100==positionsX.get(i)))&& (positionsY.get(i)==Y+200 && gY[i].isItTurn)){isItTurn=true; movability[0]=true;}}


                    if ((X-150==positionsX.get(i) || (X-100==positionsX.get(i))) && (positionsY.get(i)==Y)){isItTurn=false; movability[1]=true;}
                    else{
                    if ((X-150==positionsX.get(i) || (X-100==positionsX.get(i))) && (positionsY.get(i)>=Y-50 && positionsY.get(i)<=Y+150)){isItTurn=false; movability[1]=true;}
                    if (i<5 &&(X-150==positionsX.get(i) || (X-100==positionsX.get(i))) && (gY[i].isItTurn && positionsY.get(i)==Y-100)){isItTurn=false; movability[1]=true;}
                    if (i<5 &&(X-150==positionsX.get(i) || (X-100==positionsX.get(i))) && (!gY[i].isItTurn && positionsY.get(i)==Y+200)){isItTurn=false; movability[1]=true;}}
                }
                if (movability[0] && movability[1]) movable=false;

                if (movable) {

                    if (isItTurn) {
                        if (X <= 50) isItTurn = false;
                        if (X!=0)
                            X -= 50;
                    } else {
                        if (X >= 850) isItTurn = true;
                        if (X!=900)
                            X += 50;
                    }
                }
            } catch (InterruptedException ie) {ie.getStackTrace();}
        }
    }

    public void paint(Graphics g) {
        if(velocity==3){
            g.setColor(new Color(51,204,255));
            g.drawOval(X,Y,100,100);
        }
        else if(velocity==2)
        {
            g.setColor(Color.blue);
            g.drawOval(X,Y,100,100);
        }
        else if(velocity==1)
        {
            g.setColor(new Color(0,0,153));
            g.drawOval(X,Y,100,100);
        }
    }
}