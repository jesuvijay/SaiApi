package com.example.saiapi.fragments.api.model;

import com.google.gson.annotations.SerializedName;

public class DeviceFrames {
    @SerializedName("uplinkFrame")
    private UpLinkFrame upLinkFrame;
    @SerializedName("downlinkFrame")
    private DownlinkFrame downlinkFrame;

}
