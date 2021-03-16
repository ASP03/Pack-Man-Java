

import javax.swing.*;
import java.awt.*;

public class PopUp extends JFrame {
    PopUp(int winOrlose,int Score){
        setSize(250,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel MessagePanel = new JPanel();
        if (winOrlose==1)
            MessagePanel.add(new JTextField("YOU WİN\n YOUR SCORE : " + Score),new BorderLayout().CENTER);
        else
            MessagePanel.add(new JTextField("GAME OVER\n YOUR SCORE : "+ Score),new BorderLayout().CENTER);
        add(MessagePanel,new BorderLayout().CENTER);
        setVisible(true);
    }
}
