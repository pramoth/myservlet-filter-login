
package th.co.geniustree.learn.mavenproject1;

import java.util.HashMap;
import java.util.Map;


public class TempraryUserStorage {
    //database
    public static Map<String,Object>  userDatabase =  new HashMap<>();
    
    //session stirage
    public static Map<String,String>  sessionStorage =  new HashMap<>();
    //static initializer block
    static {
        userDatabase.put("pramoth", "computer");
    }
    
    //instance initializer block
    
    {
        System.out.println("xxxx");
    }
    
    TempraryUserStorage(){
        System.out.println("yyyyy");
    }
    
}
