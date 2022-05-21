package dz.univ.bechar.mda.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.AssertTrue;
import java.net.URI;
import java.util.logging.Logger;

@SpringBootTest
public class VideoServiceTest {
    @Autowired
    VideoService service;
    @Test
    public void givenId_when_Creating_stream_link(){
        String id= "p5GH7";
        URI uri= service.streamLink(id);
        System.out.println(uri);

    }
    @Test
    public void givenId_when_Creating_thumbnail_link(){
        String id= "p5GH7";
        URI uri= service.thumbnailLink(id);
        System.out.println(uri);
    }


}
