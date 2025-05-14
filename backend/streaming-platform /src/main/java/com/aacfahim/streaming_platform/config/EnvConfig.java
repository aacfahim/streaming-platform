package com.aacfahim.streaming_platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "file:env.properties", ignoreResourceNotFound = true)
})
public class EnvConfig {
}
