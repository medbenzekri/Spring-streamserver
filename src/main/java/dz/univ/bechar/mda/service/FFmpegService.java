package dz.univ.bechar.mda.service;
import com.github.kokorin.jaffree.ffmpeg.*;
import com.github.kokorin.jaffree.ffprobe.FFprobe;
import com.github.kokorin.jaffree.ffprobe.FFprobeResult;
import dz.univ.bechar.mda.configuration.MinioConfiguration;
import dz.univ.bechar.mda.model.Genratable;
import io.minio.GetObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class FFmpegService {
    @Autowired
    private MinioConfiguration client;
    @Autowired
    private FileManagementService service;

    public class Genrator implements Genratable {
       private final InputStream stream;
       private
        Genrator(String id){
            GetObjectArgs args = GetObjectArgs.builder().bucket(client.getVideobucket()).object(id).build();
            try {
                 this.stream=client.getObject(args);

            } catch (ErrorResponseException | InsufficientDataException | InvalidKeyException | XmlParserException |
                     ServerException | NoSuchAlgorithmException | IOException | InvalidResponseException |
                     InternalException e) {
                throw new RuntimeException(e);
            }
        }
        public Duration getduration(){
           long duration;
            FFprobeResult result = FFprobe.atPath()
                    .setInput(stream)
                    .setShowStreams(true)
                    .execute();
            duration=result.getStreams().stream().findFirst().get().getDuration().longValue();
           return Duration.ofSeconds(duration);



        }
        public  Path generatethumbnail() {


            Path image =service.getTempFile();


            FFmpeg.atPath()
                    .addInput(PipeInput.pumpFrom(stream).setPosition(10L, TimeUnit.SECONDS))
                    .setOverwriteOutput(true)
                    .addOutput(UrlOutput.toPath(image).setFormat("image2").addArguments("-frames:v", "1"))
                    .execute();

            return image;
        }
    }

    public Genrator genrator(String id){
        return new Genrator(id);
    }


    private String getpath(String name){

        for (String dirname : System.getenv("PATH").split(File.pathSeparator)) {
            File file = new File(dirname, name);
            if (file.isFile() && file.canExecute()) {
                return file.getAbsolutePath();
            }
        }
        throw new AssertionError(name+" path was not found");

    }

}
