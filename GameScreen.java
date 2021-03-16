
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JFrame implements KeyListener {
    int X,Y,firstTime;
    boolean Isitfinish=false;
    static int playerX=0,playerY=0;
    Packman player= new Packman(900,900);
    ArrayList<Award> Awards = new ArrayList<Award>(10);
    static GhostXaxis[] gX=new GhostXaxis[5];
    static GhostYaxis[] gY=new GhostYaxis[5];

    Thread[] ThreadX = new Thread[5];
    Thread[] ThreadY = new Thread[5];
    static ArrayList<Integer> positionsX = new ArrayList<Integer>();
    static ArrayList<Integer> positionsY = new ArrayList<Integer>();
    Random r = new Random();
    JTextField typingArea;
    public void play(){
            firstTime= (int)System.currentTimeMillis();
        for (int i = 0; i <10 ; i++)
            Awards.add(new Award((r.nextInt(17)*50)+50,(r.nextInt(19)*50)+50));

        for (int i = 0;  i <gY.length ; i++){ X=(r.nextInt(8)*100)+100; Y=(r.nextInt(8)*100)+100;
            for (int j = 0; j <positionsX.size() ; j++){
                if (positionsX.get(j)==X && positionsY.get(j)==Y){
                    j=-1; X=(r.nextInt(8)*100)+100; Y=(r.nextInt(8)*100)+100; continue;}}
            gY[i] = new GhostYaxis(X,Y,(i%3)+1,i); positionsX.add(X); positionsY.add(Y); ThreadY[i]=new Thread(gY[i]); ThreadY[i].start();}

        for (int i = 0;  i <gX.length ; i++){
            X=(r.nextInt(8)*100)+100; Y=(r.nextInt(8)*100)+100;
            for (int j = 0; j <positionsX.size() ; j++){
                    if (positionsX.get(j)==X && positionsY.get(j)==Y){
                        j=-1; X=(r.nextInt(8)*100)+100; Y=(r.nextInt(8)*100)+100; continue;}}
            gX[i] = new GhostXaxis(X,Y,(i%3)+1,i); positionsX.add(X); positionsY.add(Y); ThreadX[i]= new Thread(gX[i]);  ThreadX[i].start();}

        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pac-Man");
        JPanel Panel = new JPanel();
        Panel.setBackground(Color.BLACK);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        typingArea = new JTextField(0);
        typingArea.addKeyListener(GameScreen.this);
        Panel.add(typingArea,new BorderLayout().SOUTH);

        add(Panel);
        setVisible(true);

        Runnable MyPaintThread = new Runnable() {
            @Override
            public synchronized void run() {
                while (true){
                    try {playerX=player.X; playerY=player.Y;
                for (int i=0;i<10;i++)
                 if (((positionsX.get(i) + 100 > playerX && playerX > positionsX.get(i)-100) && (positionsY.get(i)+100 > playerY && playerY >positionsY.get(i)-100))){
                     player.Score+=((int)System.currentTimeMillis()-firstTime)/1000; new PopUp(0,player.Score); Isitfinish=true; break;}
                if (playerX==0 && playerY==0){
                    new PopUp(1,player.Score); Isitfinish=true; break;}
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (Isitfinish)
                        break;
                    repaint();
                }
            }
        };
        MyPaintThread.run();
    }
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.RED);
        g.drawLine(0, 0, 100,100);
        g.drawLine( 0, 100,100,0);

        player.paint(g);
        for (int i = 0; i <5 && gX[i]!=null && gY[i]!=null ; i++) {
            positionsX.set(i,gX[i].X); positionsY.set(i,gX[i].Y);
            positionsX.set(i+5,gY[i].X); positionsY.set(i+5,gY[i].Y);
        }

        boolean draw;
        for (int i = 0; i <Awards.size() ; i++){ draw=true;
            for (int j = 0; j <positionsX.size() ; j++){
                if (Awards.get(i).eated ||(positionsX.get(j)==Awards.get(i).X && positionsY.get(j)==Awards.get(i).Y) || (positionsX.get(j)==Awards.get(i).X-50 && positionsY.get(j)== Awards.get(i).Y) || (positionsX.get(j)== Awards.get(i).X-50 && positionsY.get(j)== Awards.get(i).Y-50)|| (positionsX.get(j)== Awards.get(i).X && positionsY.get(j)== Awards.get(i).Y-50)){
                    draw=false; break;}}
            if (draw)
            Awards.get(i).paint(g);
        }

        for (int i = 0; i <gX.length && gX[i]!=null ; i++)
            gX[i].paint(g);
        for (int i = 0; i <gY.length && gY[i]!=null ; i++)
            gY[i].paint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && player.Y!=0) {
            player.Y-=25;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && player.Y!=900) {
            player.Y+=25;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && player.X!=900)/*600*/ {
            player.X+=25;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && player.X!=0) {
            player.X-=25;
        }
       for (int i = 0; i < Awards.size(); i++) {
            if((player.X == Awards.get(i).X && player.Y == Awards.get(i).Y )|| (player.X == Awards.get(i).X -50 && player.Y == Awards.get(i).Y) || (player.X  == Awards.get(i).X-50 && player.Y == Awards.get(i).Y -50) || (player.X  == Awards.get(i).X && player.Y == Awards.get(i).Y -50)){
                Awards.get(i).eated=true; player.Score+=5;}
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyTyped(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyTyped(e);
    }
}
