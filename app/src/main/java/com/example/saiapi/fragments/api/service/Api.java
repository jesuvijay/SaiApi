package com.example.saiapi.fragments.api.service;

import com.example.saiapi.fragments.api.model.ApplicationList;
import com.example.saiapi.fragments.api.model.DeviceList;
import com.example.saiapi.fragments.api.model.JwtToken;
import com.example.saiapi.fragments.api.model.LoginRequest;
import com.example.saiapi.fragments.api.model.OrganizationList;

import java.util.Observable;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface Api {

    @POST("api/internal/login")
    Call<JwtToken> getJwt(@Body LoginRequest loginRequest);

    @GET("api/organizations")
    Call<OrganizationList> getOrganization(@Header("Grpc-Metadata-Authorization") String jwtToken, @Query("limit") String limit, @Query("offset") String offset);

    @GET("api/applications")
    Call<ApplicationList> getApplicationList(@Header("Grpc-Metadata-Authorization") String jwtToken, @Query("limit") String limit, @Query("offset") String offset, @Query("organizationID") String orgId);

    @GET("api/devices")
    Call<DeviceList> getDeviceList(@Header("Grpc-Metadata-Authorization") String jwtToken, @Query("limit") String limit, @Query("offset") String offset,
                                   @Query("applicationID") String appId, @Query("serviceProfileID") String serProfileId);

    @Streaming
    @GET("api/devices/{jsonData}/events")
    Observable<ResponseBody> streamJson(@Path("dev_eui") String jsonData);
}
