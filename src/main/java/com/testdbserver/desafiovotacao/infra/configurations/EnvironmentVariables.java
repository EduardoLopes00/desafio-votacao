package com.testdbserver.desafiovotacao.infra.configurations;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="environment.variables")
@Configuration("EnvironmentVariables")
@Data
@RequiredArgsConstructor
public class EnvironmentVariables {
    private String JWTSECRET;
}
