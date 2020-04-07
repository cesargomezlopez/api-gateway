package bootcamp.everis.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableEurekaClient
@EnableHystrix
@Configuration
public class BeanConfig {

  @Bean
  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
    return builder.routes()
            .route(r -> r.path("/client/**")
                    .filters(f -> f.hystrix(c -> c.setName("client")
                            .setFallbackUri("forward:/fallback/message")))
                    .uri("lb://MS-CLIENT/")
                    .id("ms-client"))

            .route(r -> r.path("/bankAccount/**")
                    .filters(f -> f.hystrix(c -> c.setName("bankAccount")
                            .setFallbackUri("forward:/fallback/message")))
                    .uri("lb://MS-BANK-ACCOUNT/")
                    .id("ms-bank-account"))

            .route(r -> r.path("/creditAccount/**")
                    .filters(f -> f.hystrix(c -> c.setName("creditAccount")
                            .setFallbackUri("forward:/fallback/message")))
                    .uri("lb://MS-CREDIT-ACCOUNT/")
                    .id("ms-credit-account"))
            .route(r -> r.path("/bank/**")
                    .filters(f -> f.hystrix(c -> c.setName("bank")
                            .setFallbackUri("forward:/fallback/message")))
                    .uri("lb://BANK/")
                    .id("ms-bank"))
            .build();
  }

}
