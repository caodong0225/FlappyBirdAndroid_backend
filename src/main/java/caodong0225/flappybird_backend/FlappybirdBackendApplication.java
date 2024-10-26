package caodong0225.flappybird_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jyzxc
 * @since 2024-10-26
 */
@SpringBootApplication
@MapperScan("caodong0225.flappybird_backend.mapper")
public class FlappybirdBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlappybirdBackendApplication.class, args);
    }

}
