package com.example.sgc.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fjader on 2017-10-17.
 */

public class CheckRequests extends StringRequest{
    private static final String[] urls =   {"http://10.0.2.2:8888/pineapple/mainuser.php",
                                            "http://10.0.2.2:8888/pineapple/accept.php",
                                            "http://10.0.2.2:8888/pineapple/reject.php"};

    private Map<String, String> params;
    public CheckRequests (String username,String supervisor, int url, Response.Listener<String> listener){
        super(Method.POST, urls[url], listener, null);//listener listens to whether things are carried out successfully or there is error
        params = new HashMap<>();
        params.put("username", username);
        params.put("supervisor_username", supervisor);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
