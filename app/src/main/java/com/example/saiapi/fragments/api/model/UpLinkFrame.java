package com.example.saiapi.fragments.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpLinkFrame {

    private String phyPayloadJSON;
    @SerializedName("rxInfo")
    private List<RxInfo> rxInfos;
    @SerializedName("txInfo")
    private TxInfo txInfo;
}
