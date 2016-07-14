
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristi
 */
public class Message implements Serializable
{
    Player mInfo;
            
    public int mSender;
    
    public MsgType mType; 
    
    public enum MsgType { MSG_INVALID, MSG_DISCONNECT, MSG_TOPEERS, MSG_CLIENTSET, MSG_ACK };
    public int mClientID;
   
    Message() { mType = MsgType.MSG_INVALID; }
    Message( MsgType tip) 
    {
        mType= tip;
    }
    Message(MsgType type, Player info)
    {
        mType= type;
        mInfo= info;
    }
   
   void setSender(int sender)
   {
       mSender= sender;
   }
   
   public int get_sender()
   {
       return mSender;
   }
   
   public Player getInfo()
   {
       return mInfo;
   }
}
