package com.example.saiapi.fragments.api.model;

import com.google.gson.annotations.SerializedName;

public class DeviceFrames {
    @SerializedName("uplinkFrame")
    private UpLinkFrame upLinkFrame;
    @SerializedName("downlinkFrame")
    private DownlinkFrame downlinkFrame;

    public DownlinkFrame getDownlinkFrame() {
        return downlinkFrame;
    }

    public UpLinkFrame getUpLinkFrame() {
        return upLinkFrame;
    }
}
