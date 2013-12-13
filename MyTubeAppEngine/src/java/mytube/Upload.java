/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mytube;
import java.io.IOException;
import javax.servlet.http.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import javax.jdo.PersistenceManager;
/**
 *
 * @author master
 */
public class Upload extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                        throws IOException {
        String chave = req.getParameter("chave");
        String dado = req.getParameter("dado");
        String nome = req.getParameter("nome");
        Arquivo arquivo = new Arquivo(chave,dado,nome);
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(arquivo);
        } finally {
            pm.close();
        }
    }
    
}
