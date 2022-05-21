package dz.univ.bechar.mda.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest

public class FileManagmentServiceTest {
    @Autowired
    FileManagementService service;
    @Test
    public void generatetempimage() throws IOException {
        service.gettempimage();
    }

}
