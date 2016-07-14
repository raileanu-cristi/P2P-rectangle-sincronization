
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.MemoryImageSource;
import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristi
 */
public class Screen extends JPanel implements KeyListener
{
    int         mW = 20;
    int         mH = 30;
    
    Player[]    mPlayers;
    Player      mMe;
    Integer     mNrPlayers;
    
    JFrame      mFrame;
    Container   mContentPane;
    
    public Screen(Player me,Player players[], int nrPlayers)
    {
        mPlayers= players;
        mNrPlayers= nrPlayers;
        mMe= me;
        
        gui();
        
    }
    
    private void gui()
    {
        mFrame= new JFrame();
        mFrame.setTitle("Polygon");
        mFrame.setSize(350, 250);
        
        // ------------------------ Listener ---------------------------
        mFrame.addWindowListener(
                                new WindowAdapter()
                                {
                                    public void windowClosing(WindowEvent e) 
                                    {
                                        System.exit(0);
                                    }
                                } 
                                
                                );
        // --------------------------------------------------------------
        
        mFrame.setVisible(true); 
        
       mContentPane = mFrame.getContentPane();
       mContentPane.add(this);
       
       
       KeyListener listener= this;
       
       mFrame.addKeyListener(listener);
        
    }
    
    
    public void paint(Graphics g)
    {
        g.clearRect(0, 0, 500, 500);
        drawPlayers(g, mPlayers, 10);
    }
    
    
    
    
    
    public void drawPlayers(Graphics g, Player players[], int nrPlayers)
    {
        for (int i=1; i<= nrPlayers; i++)
           if (players[i] != null)
               if (players[i] != mMe)
                    drawPlayer(g, players[i], Color.green);
               else
                   drawPlayer(g, mMe, Color.blue);
    }
    
    void drawPlayer(Graphics g, Player p, Color c)
    {
        int x= p.getX();
        int y= p.getY();
        
        g.setColor(c);
        g.fillRect(x, y, mW, mH);
    }
    
      @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        System.out.println("key pressed");
        
        if (mMe.getX() != mMe.mNext.mX || mMe.getY() != mMe.mNext.mY )
            return;
        
        int key = e.getKeyCode();
        
        int x= mMe.getX();
        int y= mMe.getY();
        int dx= 2;
        
        if (key == KeyEvent.VK_LEFT) 
        {
            mMe.setNextPoz(x-dx, y);           
        }
        if (key == KeyEvent.VK_RIGHT) 
        {
            mMe.setNextPoz(x+dx, y);
        }
        if (key == KeyEvent.VK_UP) 
        {
            mMe.setNextPoz(x, y-dx);
        }
        if (key == KeyEvent.VK_DOWN) 
        {
            mMe.setNextPoz(x, y+dx);
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
   
}
