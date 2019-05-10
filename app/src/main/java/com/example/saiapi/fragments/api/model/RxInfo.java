package com.example.saiapi.fragments.api.model;

import com.google.gson.annotations.SerializedName;

public class RxInfo {
    private int antenna,board,channel;
    @SerializedName("encryptedFineTimestamp")
    private EncryptedFineTimestamp encryptedFineTimestamp;
    private String fineTimestampType,gatewayId;
    @SerializedName("location")
    private Location location;
    private String loraSnr,time,timeSinceGpsEpoch;
    @SerializedName("plainFineTimestamp")
    private PlainFineTimestamp plainFineTimestamp;
    private int rfChain,rssi,timestamp;

}
