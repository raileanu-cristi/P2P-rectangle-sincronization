
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class Connection extends Thread
{
    Socket mSocket;
    Message msg;
    ObjectOutputStream[] mOutputs;
    int mId;
    
    String ok= "[ok] ";
    String er= "[!] ";
    String pend= "[...] ";
    
    public Connection(Socket s, ObjectOutputStream[] outputs, int id)
    {
        mSocket= s;
        mOutputs= outputs;
        mId= id;
    }
    
    public Message getMessage()
    { 
        return msg;
    }
    
    public void run()
    {
        try 
        {
            //System.out.println(pend+"asociating object output stream...");
            
            ObjectOutputStream streamToClient = new ObjectOutputStream(mSocket.getOutputStream());
            
            //System.out.println(pend+"asociating object input stream...");
            
            ObjectInputStream streamFromClient = new ObjectInputStream(mSocket.getInputStream()); 
            
            mOutputs[mId]= streamToClient;
            
            //System.out.println(ok+"socket i/o created! ");
            
            System.out.println(pend+"Sending configurations");
            
            msg= new Message(Message.MsgType.MSG_CLIENTSET);
            msg.setSender(mId);
            
            streamToClient.writeObject(msg);
            
            System.out.println(ok+"configurations sent to "+mSocket.getRemoteSocketAddress());
            
            boolean stillConnected = true;
            
           
            
            //  While loop
            while(stillConnected)   
            {         
                msg= null;
                //System.out.println(pend+"wait for client messages");
                
                msg = (Message) streamFromClient.readObject();
                
                
                //System.out.println(ok+"message received! processing...");
                                
                switch(msg.mType)
                {
                    case MSG_DISCONNECT:
                    {
                        System.out.println("client got disconnected..");                        
                        stillConnected = false;
                        mSocket.close();
                    }
                    break;
                    
                    case MSG_TOPEERS:
                    {
                        //System.err.println("[ACHTUNG !!! ] sending message to all other peers: "+msg.getInfo());
                        
                        for (int i=1; i< mOutputs.length; i++)
                            if (i != mId && mOutputs[i] != null)
                            {
                                mOutputs[i].writeObject(msg);
                                //System.out.println(ok+"Message sent to client "+i);
                            }
                    }
                    break;
                }
                
                
            }            
        }
        catch(Exception e)
        {
            System.out.println(er+"Client nr "+mId+ " disconnected! "+ e.getClass());
            mOutputs[mId]= null;
            this.interrupt();
        }
    }
}
