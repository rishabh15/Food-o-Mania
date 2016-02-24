package com.ekart.hackfest.foodmania.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by rishabh.sood on 23/02/16.
 */
public class SystemConfiguration extends Configuration{
    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory slaveDb = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return slaveDb;
    }
}

