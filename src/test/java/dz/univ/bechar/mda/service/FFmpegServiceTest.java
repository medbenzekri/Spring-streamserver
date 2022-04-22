package dz.univ.bechar.mda.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FFmpegServiceTest {
    @Autowired
    FFmpegService service;
    @Test
    public void givenObjectStream_whenCalculate_duration() throws  Exception{
        Duration realduration
                = Duration.parse("PT20S");
        Duration duration = service.genrator("Monkeys360.mp4").getduration();

        assertThat(duration.getSeconds()).isEqualTo(realduration.getSeconds());
    }
}
