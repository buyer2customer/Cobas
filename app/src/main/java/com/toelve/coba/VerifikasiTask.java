package com.toelve.coba;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

class VerifikasiTask {
   private Context ctx;
   private SharedPreferences boyprefs;
   private Dialog load;
   private ArrayList<String> RoleId=new ArrayList<>();
    VerifikasiTask(Context ctx, Dialog load) {
        this.ctx = ctx;
        this.load = load;
    }


    void requestAction(String token) {
        load.dismiss();
        boyprefs = ctx.getSharedPreferences(ctx.getResources().getString(R.string.prefensi), Context.MODE_PRIVATE);
        RequestQueue queue = Volley.newRequestQueue(ctx);

        String POST_ORDER = ctx.getResources().getString(R.string.veremail);


        try {
            RequestQueue requestQueue = Volley.newRequestQueue(ctx);
            String URL = POST_ORDER;
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("token", token);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ctx.startActivity(new Intent(ctx,MainActivity.class));
                    ((Activity)ctx).finish();
                    Log.i("voli", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    NetworkResponse response = error.networkResponse;
                    if (error instanceof ServerError && response != null) {
                        try {
                            String res = new String(response.data,
                                    HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                            // Now you can use any deserializer to make sense of data
                            JSONObject obj = new JSONObject(res);
                            Log.e("obj",obj.getString("message"));
                            String hasil=obj.getString("message");
                            Log.e("hasil",hasil);
                            ctx.startActivity(new Intent(ctx,MainActivity.class));
                            ((Activity)ctx).finish();
                                Toast.makeText(ctx, obj.getString("message"), Toast.LENGTH_SHORT).show();




                        } catch (UnsupportedEncodingException e1) {
                            // Couldn't properly decode data to string
                            e1.printStackTrace();
                        } catch (JSONException e2) {
                            // returned data is not JSONObject?
                            e2.printStackTrace();
                        }
                    }
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected void deliverResponse(String response) {
                    super.deliverResponse(response);
                    Log.e("d",response);
                }


            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
