package com.turan.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties
public class WholeConfigs {

    @Value("${properties.uri.web}")
    public String webb;
    @Value("${properties.notification.webHook}")
    public String webHookk;


    public WholeConfigs() {

    }
}