package com.example.saiapi.fragments.api.model;

import com.google.gson.annotations.SerializedName;

public class TxInfo {
    private int antenna, board, frequency;
    @SerializedName("fskModulationInfo")
    private FskModulationInfo fskModulationInfo;
    private String gatewayId;
    private boolean immediately;
    @SerializedName("loraModulationInfo")
    private LoraModulationInfo loraModulationInfo;
    private String modulation, timeSinceGpsEpoch;
    private int power, timestamp;

}
