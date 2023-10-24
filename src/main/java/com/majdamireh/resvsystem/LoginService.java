package com.majdamireh.resvsystem;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LoginService {

    private static HashMap<String,String> users = new HashMap();

    static
    {
        users.put("majd", "testing1");
        users.put("leen","Testing2");
        users.put("zaidooo", "Testing3");
    }

    public boolean checkCredentials(Map.Entry<String,String> credentials)
    {
      if(!users.containsKey(credentials.getKey()))
      {
          return false;
      }

      return users.get(credentials.getKey()).equals(credentials.getValue());
    }

}
