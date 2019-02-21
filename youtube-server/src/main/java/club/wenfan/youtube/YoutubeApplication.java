package club.wenfan.youtube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/7 12:49
 */
@SpringBootApplication
@EnableScheduling  //开启定时任务
@EnableCaching
@EnableRedisHttpSession(redisNamespace = "youtube")
public class YoutubeApplication {

    public static void main(String args[]){
        SpringApplication.run(YoutubeApplication.class , args);
    }

}
