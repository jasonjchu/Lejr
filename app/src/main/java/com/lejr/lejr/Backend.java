package com.lejr.lejr;

/**
 * Created by jason on 20/01/18.
 */

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Backend {
}


public interface register {
    @POST("/register")
    Call<List<String>>
}