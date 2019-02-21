package club.wenfan.youtube.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/14 20:46
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
}
