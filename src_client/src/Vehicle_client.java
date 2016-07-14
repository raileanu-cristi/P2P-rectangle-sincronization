/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Cristi
 */
public class Vehicle_client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String serverAddress= "localhost";
        int port= 9090;
        
        if (args.length >= 2)
        {
            serverAddress= args[0];
            port= Integer.parseInt(args[1]);
        }
        
        //new NetworkGame(serverAddress, port).run();
        new NetworkGame(serverAddress, port).run();
        
        
    }
    
}
