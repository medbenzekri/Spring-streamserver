package dz.univ.bechar.mda.service;
import dz.univ.bechar.mda.configuration.MinioConfiguration;
import dz.univ.bechar.mda.entity.Video;
import dz.univ.bechar.mda.model.Genratable;
import dz.univ.bechar.mda.repository.VideoRepository;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Event;
import io.minio.messages.NotificationRecords;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import java.net.URI;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.stream.Stream;

@EnableAsync
@Service
public class StorageService {


    @Autowired
    private VideoRepository repository;
    @Autowired
    private MinioConfiguration client;
    @Autowired
    private VideoService service;
    @Autowired
    FFmpegService fFmpegService;
    @Autowired
    FileManagementService fileService;
    @Autowired
    UploadService uploadService;
    public boolean Store(MultipartFile file) throws Exception {

            System.out.println(file.getName()+" was uploaded.");
            Genratable generator=fFmpegService.genrator(file.getName());
            Video video = VideoBuilder(file,generator);
            System.out.println(video.toString());
            Path image= generator.generatethumbnail();
            uploadService.uploadthumbnail(image,video.getCode());
            fileService.deletetemp(image);
            save(video);
            uploadService.uploadvideo(file);
            return true;
    }


    protected Video VideoBuilder(MultipartFile file,Genratable genrator){
        String code= service.gencode();
        URI stream= service.streamLink(code);
        String size= FileUtils.byteCountToDisplaySize(file.getSize());
        Duration duration =  genrator.getduration();
        URI thumbnail = service.thumbnailLink(code);
        Video video= new Video().setName(file.getName())
                .setSize(size).setCode(code).setStream(stream)
                .setThumbnail(thumbnail).setDuration(duration);
        return video;
    }
    protected void save(Video video){repository.save(video);}





}
