package club.wenfan.youtube.config;

import club.wenfan.youtube.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/7 18:22
 */

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
