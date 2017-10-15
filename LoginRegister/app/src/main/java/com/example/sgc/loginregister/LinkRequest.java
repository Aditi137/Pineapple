package com.example.sgc.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fjader on 2017-10-15.
 */

public class LinkRequest extends StringRequest {
    private static final String Link_Accounts_URL = "http://10.0.2.2:8888/pineapple/linkaccounts.php";

    private Map<String, String> params;
    public LinkRequest (String my_username, String supervisee_username, Response.Listener<String> listener){
        super(Method.POST, Link_Accounts_URL, listener, null);//listener listens to whether things are carried out successfully or there is error
        params = new HashMap<>();
        params.put("my_id", my_username);
        params.put("supervisee_id", supervisee_username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
