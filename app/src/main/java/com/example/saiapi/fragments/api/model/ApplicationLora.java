package com.example.saiapi.fragments.api.model;

import androidx.annotation.NonNull;

import java.util.Locale;

public class ApplicationLora {


    private String id;
    private String name;
    private String description, organizationID, serviceProfileID, serviceProfileName;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOrganizationID() {
        return organizationID;
    }

    public String getServiceProfileID() {
        return serviceProfileID;
    }

    public String getServiceProfileName() {
        return serviceProfileName;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.US,"Id:%s\nName:%s\nDescription:%s\nOrganizationId:%s\nserviceProfileId:%s\nServiceProfileName:%s",id,name,description,organizationID,serviceProfileID,serviceProfileName);
    }
}
