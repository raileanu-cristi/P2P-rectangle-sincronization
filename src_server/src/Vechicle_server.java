/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristi
 */
public class Vechicle_server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int port=9090;
        
        if ( args.length < 1 )
            System.err.println("Port was not given. Using default port 9090");
        else
            port= Integer.parseInt(args[0]);
        
        
        new P2PServer(port).run();
    }
    
}
