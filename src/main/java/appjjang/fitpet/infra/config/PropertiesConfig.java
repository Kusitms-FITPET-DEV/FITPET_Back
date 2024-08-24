package appjjang.fitpet.infra.config;

import appjjang.fitpet.infra.config.jwt.JwtProperties;
import appjjang.fitpet.infra.config.oauth.KakaoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({
        KakaoProperties.class,
        JwtProperties.class,
})
@Configuration
public class PropertiesConfig {}
