package dz.univ.bechar.mda.service;

import dz.univ.bechar.mda.configuration.MinioConfiguration;
import dz.univ.bechar.mda.entity.Video;
import io.minio.ListObjectsArgs;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:minio.properties")
public class VideoService {
    @Autowired
    MinioConfiguration client ;
    @Value("${Bucket}")
    String bucket;
    public List<Video> getVideos(){
        List<Video> videos = new ArrayList<>();
        ListObjectsArgs args= ListObjectsArgs.builder().bucket(bucket).build();
        Iterable<Result<Item>> results=client.listObjects(args);
        results.forEach(result -> {
            try {
                videos.add( new Video(result.get()) );
            } catch (ErrorResponseException | InsufficientDataException
                     | InternalException | InvalidKeyException |
                     InvalidResponseException | IOException
                     | NoSuchAlgorithmException | ServerException |
                     XmlParserException e) {
                throw new RuntimeException(e);
            }
        });

       return videos;


    }
}
