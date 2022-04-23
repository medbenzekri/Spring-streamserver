package dz.univ.bechar.mda.service;
import dz.univ.bechar.mda.configuration.MinioConfiguration;
import dz.univ.bechar.mda.controller.ImageController;
import dz.univ.bechar.mda.entity.Video;
import dz.univ.bechar.mda.repository.VideoRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import java.net.URI;

@Service
@PropertySource("classpath:minio.properties")
public class VideoService {
    @Autowired
    MinioConfiguration client ;
    @Autowired
    VideoRepository repository;

    public Page<Video> getVideos(Pageable pageable){

       return  repository.findAll(pageable);
    }

    public URI thumbnailLink(String id){
    WebMvcLinkBuilder builder= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageController.class).getthumbnail(id));
    return builder.toUri();

    }
    public String gencode(){
        int length= 4;
        boolean useLetters= true;
        boolean useNumbers= true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        if (repository.existsByCode(generatedString))
            return gencode();
        return generatedString;

    }
}
