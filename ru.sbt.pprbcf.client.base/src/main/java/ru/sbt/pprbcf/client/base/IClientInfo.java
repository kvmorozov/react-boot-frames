package ru.sbt.pprbcf.client.base;

import java.util.List;

/**
 * Created by sbt-morozov-kv on 13.02.2017.
 */
public interface IClientInfo {

    String getAppName();

    List<PluggableFeature> getFeatures();

    void setCurrentUser(String currentUser);
}
