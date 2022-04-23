package dz.univ.bechar.mda.service;

import dz.univ.bechar.mda.configuration.MinioConfiguration;
import dz.univ.bechar.mda.entity.Video;
import dz.univ.bechar.mda.model.Genratable;
import dz.univ.bechar.mda.repository.VideoRepository;
import io.minio.CloseableIterator;
import io.minio.ListenBucketNotificationArgs;
import io.minio.Result;
import io.minio.messages.Event;
import io.minio.messages.NotificationRecords;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.time.Duration;
import java.util.stream.Stream;

@Service
@PropertySource("classpath:minio.properties")
public class ManagementService {
    @Value("${Bucket}")
    private String bucket;

    @Autowired
    private Environment env;
    String[] events = {"s3:ObjectCreated:*"};
    @Autowired
    private VideoRepository repository;
    @Autowired
    private MinioConfiguration client;
    @Autowired
    private VideoService service;
    @Autowired
    FFmpegService fFmpegService;
    public void Start(){
        try(CloseableIterator<Result<NotificationRecords>> ci = client.listenBucketNotification(ListenBucketNotificationArgs.builder()
                .bucket(bucket)
                .prefix("")
                .suffix("")
                .events(events)
                .build())){

                Stream<Event> eventStream = ci.next().get().events().stream();
                eventStream.forEach(this::save);

        } catch (Exception e) {
            System.out.println("error while listening  to bucket notification ");
        }

    }
    public void save(Event object ){
        Genratable genrator=fFmpegService.genrator(object.objectName());
        OutputStream image= genrator.generatethumbnail();
        String code= service.gencode();
        String size= FileUtils.byteCountToDisplaySize(object.objectSize());
        Duration duration =  genrator.getduration();
        URI thumbnail = service.thumbnailLink(code);
        Video video= new Video().setName(object.objectName())
            .setSize(size).setCode(code)
            .setThumbnail(thumbnail).setDuration(duration);
        repository.save(video);

    }

    public void upload(){

    }

}
