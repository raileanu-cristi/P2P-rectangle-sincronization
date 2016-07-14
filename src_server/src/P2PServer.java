
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristi
 */
public class P2PServer 
{
    int mPort;
    
    Connection[] client;
    ObjectOutputStream[] mOutputs;
    
    int mMaxClientNR = 10;
    
    public P2PServer(int port)
    {
        mPort= port;
        mOutputs= new ObjectOutputStream[mMaxClientNR];
        
        client= new Connection[mMaxClientNR];
    }
    
    public void run()
    {
        try 
        {
            ServerSocket listener = new ServerSocket(mPort);
            
            while(true)
            {
                System.out.println("Waiting for a client to connect...");
                Socket socket = listener.accept();
                
                System.out.println("client "+socket.getLocalAddress());
                // TODO: notice that we can support only one client at a time...in the next app we'll use threads to solve this
                
                for (int i=1; i<client.length; i++)
                    if ( client[i] == null || client[i].isAlive() == false )
                    {
                        client[i]= new Connection(socket, mOutputs, i);
                        client[i].start();
                        
                        break;
                    }
                
                
                
                //    parseMessage(cons.pop_front());
            }           
        }
        catch(Exception e)
        {
            System.out.println("Eroare! "+e.getMessage());      
        }
    }
}
