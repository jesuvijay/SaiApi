package com.example.saiapi.fragments.api.model;

public class Organization {
    
    private String id;
    private String name;
    private String displayName;
    private boolean canHaveGateways;
    private String createdAt;
    private String updatedAt;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isCanHaveGateways() {
        return canHaveGateways;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
