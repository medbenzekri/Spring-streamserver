package dz.univ.bechar.mda.configuration;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@PropertySource("classpath:minio.properties")
@Component
public class MinioConfiguration extends MinioClient{
    @Value("${imagebucket}")
    private String imagebucket;
    @Value("${Bucket}")
    private String videobucket;
    @Autowired
     MinioConfiguration(@Value("${port}") int port , @Value("${endpoint}") String endpoint ,
    @Value("${acessKey}") String acessKey,@Value("${secretKey}") String secretKey) {
         super(MinioClient.builder()
                 .endpoint(endpoint,port,false)
                 .credentials(acessKey,secretKey).build());


    }

    public String getImagebucket() {
        return imagebucket;
    }

    public String getVideobucket() {
        return videobucket;
    }
}
