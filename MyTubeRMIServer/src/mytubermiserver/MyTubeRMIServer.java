/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytubermiserver;


import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import static java.rmi.registry.LocateRegistry.createRegistry;


/**
 *
 * @author Arthur
 */
public class MyTubeRMIServer {

    /**
     * @param args the command line arguments
     * @throws java.rmi.AlreadyBoundException
     */

    public static void main(String[] args) throws AlreadyBoundException {

        try {
            
            ServerImplementation localObject = new ServerImplementation();
            //Naming.rebind("rmi:///Rem", localObject);
       

            
            Registry reg = createRegistry(1099);
            
            reg.rebind("MyTubeRMI", localObject);
            System.out.println("Registered: " + "MyTubeRMI" + " -> " +
		reg.getClass().getName() + "[" + reg + "]");
            
            System.out.println("MyTube RMIServer Online!");
        } catch (RemoteException re) {
            System.out.println("RemoteException: " + re);
        }       
    } 
}
