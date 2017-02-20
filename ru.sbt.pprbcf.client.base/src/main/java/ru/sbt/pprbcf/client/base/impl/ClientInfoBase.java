package ru.sbt.pprbcf.client.base.impl;

import ru.sbt.pprbcf.client.base.IClientInfo;
import ru.sbt.pprbcf.client.base.PluggableFeature;

import java.util.List;

/**
 * Created by sbt-morozov-kv on 13.02.2017.
 */
public class ClientInfoBase implements IClientInfo {

    protected String appName;
    protected String url;
    protected List<PluggableFeature> features;
    protected String currentUser;

    public ClientInfoBase(String appName, List<PluggableFeature> features) {
        this.appName = appName;
        this.features = features;
    }

    public ClientInfoBase(String appName, String url) {
        this.appName = appName;
        this.url = url;
    }

    @Override
    public String getAppName() {
        return appName;
    }

    @Override
    public List<PluggableFeature> getFeatures() {
        return features;
    }

    @Override
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
