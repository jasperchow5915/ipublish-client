/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import conferenceentities.Coreconf;
import java.util.ArrayList;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
/**
 *
 * @author lingjunqiu
 */
public class Bridge {

        private ArrayList<Coreconf> records;
        Ipublish ipublish = new Ipublish();
    public ArrayList<Coreconf> getByTitle(String title) {
           GenericType<ArrayList<Coreconf>> genericType = new GenericType<ArrayList<Coreconf>>() {
                   };
                Response res = ipublish.findByTitle_XML(Response.class, title);
                   records = res.readEntity(genericType);

        return records;
    }
    
        public ArrayList<Coreconf> getByAcronym(String acronym) {
           GenericType<ArrayList<Coreconf>> genericType = new GenericType<ArrayList<Coreconf>>() {
                   };
                Response res = ipublish.findByAcronym_XML(Response.class, acronym);
                   records = res.readEntity(genericType);

        return records;
    }
        
        public ArrayList<Coreconf> getByRank(String rank) {
           GenericType<ArrayList<Coreconf>> genericType = new GenericType<ArrayList<Coreconf>>() {
                   };
                Response res = ipublish.findByRank_XML(Response.class, rank);
                   records = res.readEntity(genericType);

        return records;
    }
        
        public ArrayList<Coreconf> getByForcode(String forcode) {
           GenericType<ArrayList<Coreconf>> genericType = new GenericType<ArrayList<Coreconf>>() {
                   };
                Response res = ipublish.findByForcode_XML(Response.class, forcode);
                   records = res.readEntity(genericType);

        return records;
    }
        
        public ArrayList<Coreconf> getAll(){
            GenericType<ArrayList<Coreconf>> genericType = new GenericType<ArrayList<Coreconf>>() {
                   };
             Response res = ipublish.findAll_XML(Response.class);
                   records = res.readEntity(genericType);
            return records;
            
        }
}
