package com.example.saiapi.api.service;

import com.example.saiapi.api.model.ApplicationList;
import com.example.saiapi.api.model.DeviceList;
import com.example.saiapi.api.model.JwtToken;
import com.example.saiapi.api.model.LoginRequest;
import com.example.saiapi.api.model.OrganizationList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

}
