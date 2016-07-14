
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristi
 */
public class NetworkGame 
{
    String ok= "[ok] ";
    String er= "[!] ";
    String pend= "[...] ";
        
    String mServerAddress;
    int mPort;
    
    public NetworkGame(String serverAdress, int port)
    {
        mServerAddress= serverAdress;
        mPort= port;
    }
    
    public void run()
    {
        
        try
        {
            System.out.println(pend+"connecting to "+ mServerAddress + ":" + mPort+"...");
            
            Socket clientSocket = new Socket(mServerAddress, mPort);
            
            System.out.println(ok+"socket created!");
            
            ObjectOutputStream streamToServer = new ObjectOutputStream(clientSocket.getOutputStream());
                      
            ObjectInputStream streamFromServer = new ObjectInputStream(clientSocket.getInputStream());
            
            System.out.println(ok+"Connected!");
            
            System.out.println(pend+"receive configurations from Server");
            
            Message msg= (Message)streamFromServer.readObject();
            
            System.out.println(ok+"Configurations received! id= "+msg.mSender);
            
            
            
            int id= msg.get_sender();
          
            Scanner scan = new Scanner(System.in);
            
            System.out.println("Input your name and initial position (x y)");
            
            String command, tokens[], nickname;
            
            System.out.print(">> ");
            command= scan.nextLine();
            
            tokens= command.split(" ");
            nickname = tokens[0];
            
            int x, y;
            x= Integer.parseInt(tokens[1]);
            y= Integer.parseInt(tokens[2]);
            
            // creating the game
            Game myGame= new Game();
            
            myGame.setMyPlayer(nickname, x, y, id);
            
            
            
            
            while(true)
            {
                // telling my position -----------------------------------------
                
                System.out.println(pend+"Sending my position");
                
                Player info= new Player(myGame.getMyPlayer());
                msg = new Message(Message.MsgType.MSG_TOPEERS, info);
                //
                System.out.println("my player is: "+msg.getInfo());
                //
                streamToServer.writeObject(msg);
                System.out.println(ok+"position sent! ");
                
                // ---------------------------------------------------------
                
                System.out.println(pend+"waiting for messages from server");
                
                Object obj= streamFromServer.readObject();
                msg= (Message) obj;
                
                System.out.println(ok+"Message received!");
                
                if (msg.mType == Message.MsgType.MSG_INVALID)
                {
                    System.out.println(er+"Incorrect message format, try again");
                }
                else
                {                   
                    // Writting message to server
                    
                    if (msg.mType == Message.MsgType.MSG_DISCONNECT)    // Normally we should wait for disconnect confirm for server but this is fine too.
                    {
                        System.out.println(pend+"disconnecting...");
                        break;
                    }
                    
                    // Waiting for his answer
                    // NOTICE that we can't get any request from client console before we receive the answer :(
                    // In the next app we'll use threads to fix this !
                    if (msg.mType == Message.MsgType.MSG_TOPEERS)    // Normally we should wait for disconnect confirm for server but this is fine too.
                    {
                        //
                        System.out.println("the remote player is "+msg.getInfo());
                        //
                        myGame.setPlayer(msg.getInfo());
                        myGame.runFrame();
                        
                        
                    }
                                        
                }
                
                
            }
                              
            
        }
        catch (Exception e)
        {
            System.out.println(er+"Error! "+ e.getClass());
            
            System.out.println(pend+"Trying again...");
            //run();
        }
    }
}
