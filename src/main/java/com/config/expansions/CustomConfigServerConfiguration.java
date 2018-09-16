package com.config.expansions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import 之后马上进行初始化
@Configuration
public class CustomConfigServerConfiguration {
    public CustomConfigServerConfiguration() {
        System.out.println("CustomConfigServerConfiguration --> init CustomConfigServerConfiguration after be imported");
    }

    @Bean
    public CustomConfigServerConfiguration.Marker enableConfigServerMarker() {
        System.out.println("CustomConfigServerConfiguration --> init marker");
        return new CustomConfigServerConfiguration.Marker();
    }

    class Marker {
        Marker() {
        }
    }
}