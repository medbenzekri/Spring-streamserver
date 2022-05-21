package dz.univ.bechar.mda.service;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileManagementService {
    public Path getTempFile() {
        Path tmpNoPrefixSuffix;
        try {
            tmpNoPrefixSuffix = Files.createTempFile(null, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tmpNoPrefixSuffix;
    }
    public void deletetemp(Path image){
        try {
            Files.deleteIfExists(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Path gettempimage() throws IOException {
            URL imageurl= URI.create("https://picsum.photos/200/300").toURL();
            BufferedImage image= ImageIO.read(imageurl);
            Path path=this.getTempFile();
            ImageIO.write( image,"jpeg",path.toFile());
            return path;
    }
}
