import ProjSD.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class Client
{
  static IdServer IdServerImpl;

  public static void main(String args[])
    {
      try{
        // create and initialize the ORB
	String param[] = new String[3];
	param[0] = "Client";
        param[1] = "-ORBInitialPort";
        param[2] = "2809";
        ORB orb = ORB.init(param, null);

        // get the root naming context
        org.omg.CORBA.Object objRef = 
            orb.resolve_initial_references("NameService");
        // Use NamingContextExt instead of NamingContext. This is 
        // part of the Interoperable naming Service.  
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
        NameComponent nc =  new NameComponent("test", "my_context");
        NameComponent nc2 = new NameComponent("mytube", "Object");
           
        NameComponent path[] = { nc,nc2 };
        IdServerImpl = IdServerHelper.narrow(ncRef.resolve(path));
	
        String chave = IdServerImpl.novaChave();
	System.out.println("Chave Recebida: "+chave);
	IdServerImpl.verificaChave(chave);
        

        } catch (Exception e) {
          System.out.println("ERROR : " + e) ;
          e.printStackTrace(System.out);
          }
    }

}
