package utils;

import java.util.HashMap;
import java.util.Set;


/*
 * Generally would not wrap a hashmap in a class like this
 * but stubbing it out to separate the creation of the the list which in a real life implementation likely be coming from
 * a data store of some sort, and DAO Functions would extract and return them.
 * Probably would look into making the Tool KEYS an ENUM or some other thing in a real world situation, but this is just a quick coding exercise so.
 */
public class Tools {

    HashMap<String,Tool> tools  = new HashMap<>();

    public Tools(){
        Tool t1 = new Tool("CHNS","Chainsaw","Stihl");
        Tool t2 = new Tool("LADW", "Ladder","Werner");
        Tool t3 = new Tool("JAKD","Jackhammer","DeWalt");
        Tool t4 = new Tool("JAKR","Jackhammer","Rigid");
        tools.put("CHNS",t1);
        tools.put("LADW",t2);
        tools.put("JAKD",t3);
        tools.put("JAKR",t4);
    }

    public Tool get(String key){
        return tools.get(key);
    }
    public Set<String> getKeys(){
       return tools.keySet();
    }
}


