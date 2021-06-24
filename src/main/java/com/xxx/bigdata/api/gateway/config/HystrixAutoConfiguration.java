package com.xxx.bigdata.api.gateway.config;

import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.config.GatewayClassPathWarningAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.DispatcherHandler;
import rx.RxReactiveStreams;

@Configuration
@ConditionalOnProperty(name = "spring.cloud.gateway.enabled", matchIfMissing = true)
@EnableConfigurationProperties
@AutoConfigureBefore(HttpHandlerAutoConfiguration.class)
@AutoConfigureAfter(GatewayClassPathWarningAutoConfiguration.class)
@ConditionalOnClass(DispatcherHandler.class)
public class HystrixAutoConfiguration {
    @Configuration
    @ConditionalOnClass({HystrixObservableCommand.class, RxReactiveStreams.class})
    protected static class HystrixConfiguration {
        @Bean
        public HystrixGatewayFilterFactory hystrixGatewayFilterFactory(DispatcherHandler dispatcherHandler) {
            return new HystrixGatewayFilterFactory(dispatcherHandler);
        }
    }
}
