/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytubermiserver;


import mytubermiserver.rmi.ServerImplementation;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;


/**
 *
 * @author Arthur
 */
public class MyTubeRMIServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            ServerImplementation localObject = new ServerImplementation();
            //Naming.rebind("rmi:///Rem", localObject);
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("MyTubeRMI", localObject);
            
            System.out.println("MyTube RMIServer Online!");
        } catch (RemoteException re) {
            System.out.println("RemoteException: " + re);
        }       
    }
}
