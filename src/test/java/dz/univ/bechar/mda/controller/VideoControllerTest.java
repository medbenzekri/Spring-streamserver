package dz.univ.bechar.mda.controller;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class VideoControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Test
    public void whenFileUploaded_thenVerifyStatus() throws Exception {
        File video = new File("/home/medbenzekri/Videos/samples/Monkeys360.mp4");
        InputStream stream = new FileInputStream(video);
        MockMultipartFile file
                = new MockMultipartFile(
                "video",
                "video.mp4",
                "video/mp4",
                stream
        );

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/video/upload").file(file))
                .andExpect(status().isOk());
    }
    }

