package com.cheatHub.configuration;

import java.util.Collections;


import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

@EnableHazelcastHttpSession
public class HazelcastConfiguration {
	@Bean
    public Config config() {

        Config config = new Config();

        JoinConfig joinConfig = config.getNetworkConfig().getJoin();

        joinConfig.getMulticastConfig().setEnabled(true);
        

        return config;
    }
}
