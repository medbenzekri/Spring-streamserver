package dz.univ.bechar.mda.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FFmpegServiceTest {
    @Autowired
    FFmpegService service;
    @Test
    public void givenObjectStream_whenCalculate_duration() {
        Duration realduration
                = Duration.parse("PT20S");
        Duration duration = service.genrator("Monkeys360.mp4").getduration();

        assertThat(duration.getSeconds()).isEqualTo(realduration.getSeconds());
    }
    @Test
    public void givenpath_whenGenerateThumbnail_thenprintpath()  {
       Path image= service.genrator("Monkeys360.mp4").generatethumbnail();
        System.out.println(image.toString());

    }
}
