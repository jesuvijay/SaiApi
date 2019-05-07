package com.example.saiapi.fragments.api.model;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Device {

    private String applicationID, description, devEUI, deviceProfileID, deviceProfileName,
            deviceStatusBattery, deviceStatusBatteryLevel, deviceStatusBatteryLevelUnavailable,
            deviceStatusExternalPowerSource, deviceStatusMargin, lastSeenAt, name;


    public String getLastSeenAt() {
        return lastSeenAt;
    }

    public String getName() {
        return name;
    }


    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.US, "ApplicationId:%s\nName:%s\nlastSeenAt:%s\nDescription:%s\nDevEUI:%s\nDeviceProfileId:%s\nDeviceProfileName:%s\nDeviceStatusBattery:%s\nDeviceStatusBatteryLevel:%s\ndeviceStatusBatteryLevelUnavailable:%s\nDeviceExternalPowerSorce:%s\n",
                applicationID, name, lastSeenAt, description, devEUI, deviceProfileID, deviceProfileName, deviceStatusBattery, deviceStatusBatteryLevel, deviceStatusBatteryLevelUnavailable, deviceStatusExternalPowerSource);
    }
}
