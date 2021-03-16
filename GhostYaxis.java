

import java.awt.*;

public class GhostYaxis extends GameScreen implements Runnable{
    int velocity,X,Y,ID;
    boolean isItTurn=false,movable=true;
    GhostYaxis(int x,int y,int speed,int id){
        X=x; Y=y; velocity=speed; ID=id;
    }
    @Override
    public synchronized void run() {

        while(true) {
            try{
                if (velocity == 3)
                    Thread.sleep(1000);
                else if (velocity == 2)
                    Thread.sleep(2000);
                else if (velocity == 1)
                    Thread.sleep(4000);


                for (int i = 0; i <positionsX.size() ; i++)
                    if(i!=ID){
                        if ((Y+100==positionsY.get(i) || (Y+150==positionsY.get(i)))&& (positionsX.get(i)>=X-50 && positionsX.get(i)<=X+50)){isItTurn=true;}
                        else if ((Y-100==positionsY.get(i) || (Y-150==positionsY.get(i))) && (positionsX.get(i)>=X-50 && positionsX.get(i)<=X+50)){isItTurn=false;}
                        if (((Y+100==positionsY.get(i) || (Y+150==positionsY.get(i)))&& (positionsX.get(i)>=X-50 && positionsX.get(i)<=X+50)) &&  ((Y-100==positionsY.get(i) || (Y-150==positionsY.get(i))) && (positionsX.get(i)>=X-50 && positionsX.get(i)<=X+50)))
                            movable=false;
                    }
               if (movable){
                   if (isItTurn)
                   {if (Y<=0) isItTurn=false; if (Y!=0)Y-=50; }
                   else{if (Y>=850) isItTurn=true; if (Y!=900) Y+=50;}
               }
            }
            catch (InterruptedException ie)
            {ie.getStackTrace();}
        }
    }

    public void paint(Graphics g) {
        if(velocity==3){
            g.setColor(Color.PINK);
            g.drawOval(X,Y,100,100);
        }
        else if(velocity==2)
        {
            g.setColor(Color.RED);
            g.drawOval(X,Y,100,100);
        }
        else if(velocity==1)
        {
            g.setColor(new Color(102,0,153));
            g.drawOval(X,Y,100,100);
        }
    }
}
