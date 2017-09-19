package com.example.sgc.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SGC on 18/9/17.
 */

public class RegisterRequest extends StringRequest{

    private static final String Register_Request_URL = "/*domainurl/Register.php*/";

    private Map<String, String> params;
    public RegisterRequest (String name, String email, String username, String password, Response.Listener<String> listener){
        super(Method.POST, Register_Request_URL, listener, null);//listener listens to whether things are carried out successfully or there is error
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("username", username);
        params.put("password", password);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
