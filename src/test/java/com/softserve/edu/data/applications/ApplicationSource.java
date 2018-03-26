package com.softserve.edu.data.applications;

public class ApplicationSource implements IApplicationSource {
    private String browserName;
    private String driverPath;
    private String baseUrl;

    // TODO Develop Builder
    public ApplicationSource(String browserName, String driverPath, String baseUrl) {
        this.browserName = browserName;
        this.driverPath = driverPath;
        this.baseUrl = baseUrl;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}