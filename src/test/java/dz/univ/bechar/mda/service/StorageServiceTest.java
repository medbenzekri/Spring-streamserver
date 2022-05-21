package dz.univ.bechar.mda.service;

import dz.univ.bechar.mda.configuration.MinioConfiguration;

import io.minio.messages.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.util.stream.Stream;


@SpringBootTest
public class StorageServiceTest {
    @Autowired
    MinioConfiguration client;
    @Autowired
    FFmpegService service;
    @Autowired
    StorageService storageService;
    @Autowired
    VideoService videoService;
    @Autowired
    UploadService uploadService;
    @Test
    public void givenImagePath_whenUploadimage_thenUploadScessfully() {

        String objectname= "TravelDreamImagine.mp4";
        Path image= service.genrator(objectname).generatethumbnail();
        String code = videoService.gencode();
        uploadService.uploadthumbnail(image,code);

    }


}
