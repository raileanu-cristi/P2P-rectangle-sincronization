
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
public class Player implements Serializable
{
    public class Position implements Serializable
    {
        int mX, mY;
    
        public Position(int x, int y)
        {
            mX= x;
            mY= y;
        }
    }
    
    String      mNickName;
    Position    mPozition;
    Position    mNext;
    int         mId;
    
    public Player(int x, int y)
    {
        mPozition= new Position(x, y);
        mNext= new Position(x, y);
    }
    
    public Player(Player p)
    {
        mNickName= new String(p.mNickName);
        mId= p.get_Id();
        mPozition= new Position(p.getX(), p.getY());
        mNext= new Position(p.getX(), p.getY());
    }
    
    public void set_poz(int x, int y)
    {
        mPozition.mX= x;
        mPozition.mY= y;
        setNextPoz(x, y);
    }
    
    public void setNextPoz(int x, int y)
    {
        mNext.mX= x;
        mNext.mY= y;
    }
    
    public void setNext()
    {
        mPozition= mNext;
    }
    
    public String toString()
    {
        return "Player "+ mNickName+" id= "+mId+ " x= "+mPozition.mX+ " y= "+mPozition.mY;
    }
    
    public int get_Id()
    {
        return mId;
    }
    
    public int getX()
    {
        return mPozition.mX;
    }
    
    public int getY()
    {
        return mPozition.mY;
    }
}
