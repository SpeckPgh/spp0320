package utils;

import java.util.HashMap;

/*
 * Generally would not wrap a hashmap in a class like this
 * but stubbing it out to separate the creation of the the list which in a real life implementation likely be coming from
 * a data store of some sort, and DAO Functions would extract and return them.
 */

public class Types {

     HashMap<String,Type> types = new HashMap<>();

    public Types(){
        Type ty1 = new Type(1.99f,true,true,false);
        Type ty2 = new Type(1.49f,true,false,true);
        Type ty3 = new Type(2.99f,true,false,false);

        types.put("Ladder",ty1);
        types.put("Chainsaw",ty2);
        types.put("Jackhammer",ty3);
    }

    public Type get(String key){
        return(types.get(key));
        
    }

}
