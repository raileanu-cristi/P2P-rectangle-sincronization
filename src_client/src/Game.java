
import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristi
 */
public class Game extends JFrame implements KeyListener
{
    int         mMaxPlayerNr;
    Player      mMe;
    Player[]    mPlayers;
    Integer     mNrPlayers;
    
    String      delim= "##############";
    
    Screen      mScreen;
    
    public Game()
    {
        mMe= new Player(0,0);
        mMaxPlayerNr= 100;
        mPlayers= new Player[mMaxPlayerNr];
            
        mNrPlayers= 0;
     
        mScreen=  new Screen(mMe,mPlayers, mNrPlayers);
        
        //KeyListener listener= this;
       
        //this.addKeyListener(listener);
    }
    
    public void setPlayer(Player p)
    {
        if (mPlayers[p.get_Id()] == null)
            mNrPlayers++;
        
        mPlayers[p.get_Id()]= p;
    }
    
    public Player getMyPlayer()
    {
        return mMe;
    }
    
    public void setMyPlayer(String nickname, int x, int y, int id)
    {
        if (mMe == null)
        {
            mMe= new Player(x, y);
            
            mNrPlayers++;
        }
        else
            mMe.set_poz(x, y);  
        
        mPlayers[id]= mMe;
        mMe.mId= id;
        mMe.mNickName= nickname;
        
        mScreen.repaint();
    }
    
    public void runFrame()
    {
        boolean verbose= false;
        
        //int x= mMe.getX();
        //int y= mMe.getY();
        //mMe.set_poz(x+5, y);
        
        if (verbose == true)
        {
            System.out.println(delim);
        
            mPlayers[0]= mMe;

            for (int i=1; i< mPlayers.length; i++ )
                if (mPlayers[i] != null)
                    System.out.println(mPlayers[i]);
            System.out.println(delim);
        }
        
        mMe.setNext();
        
        mScreen.repaint();
        
        try 
        {
            Thread.sleep(30);                 //1000 milliseconds is one second.
        } 
        catch(InterruptedException ex) 
        {
                Thread.currentThread().interrupt();
        }
        
        
        
        // sleep a litle
        
        
        
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
