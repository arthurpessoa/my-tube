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
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


import java.util.List;
import javax.jdo.PersistenceManager;
//import javax.jdo.Query;
/**
 *
 * @author master
 */
public class Serve extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
              throws IOException {
        //String method = req.getParameter("method");
        String key = req.getParameter("chave");
        List<Entity> results;
            //javax.jdo.Query query = pm.newQuery("Arquivo");
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            //Entity result;
            Filter filtro = new FilterPredicate("chave",Query.FilterOperator.EQUAL,key);
            Query q = new Query("Arquivo").setFilter(filtro);
            //String query = "select * from " + Arquivo.class.getName() + " where chave == '"+key+ "'";
            //String query = "select from " + Arquivo.class.getName() + " order by date desc range 0,5";
            results = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(5));
            try{
                //results = (List<Arquivo>)q.execute(key);
                if (results.isEmpty()){
                    System.out.println("Query nao retornou nada");
                }
            }finally{
                //q.closeAll();
            }  
              PrintWriter out = resp.getWriter();
              for(Entity e : results){
                  out.println(e.getProperty("dado"));
                  out.println(e.getProperty("nome"));
              }
              
        }
    }

