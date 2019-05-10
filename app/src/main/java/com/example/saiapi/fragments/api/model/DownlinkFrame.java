package com.example.saiapi.fragments.api.model;

import com.google.gson.annotations.SerializedName;

public class DownlinkFrame {

    private String phyPayloadJSON;
    @SerializedName("txInfo")
    private TxInfo txInfo;
}
