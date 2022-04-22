package dz.univ.bechar.mda.service;
import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
import com.github.kokorin.jaffree.ffmpeg.PipeOutput;
import com.github.kokorin.jaffree.ffprobe.FFprobe;
import com.github.kokorin.jaffree.ffprobe.FFprobeResult;
import com.github.kokorin.jaffree.ffmpeg.PipeInput;
import dz.univ.bechar.mda.configuration.MinioConfiguration;
import dz.univ.bechar.mda.model.Genratable;
import io.minio.GetObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;

@Service
public class FFmpegService {
    @Autowired
    private MinioConfiguration client;

    public class Genrator implements Genratable {
       private final InputStream stream;
       private
        Genrator(String id){
            GetObjectArgs args = GetObjectArgs.builder().bucket(client.getVideobucket()).object(id).build();
            try {
                 this.stream=  client.getObject(args);
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
        public  OutputStream generatethumbnail(){
           OutputStream image = new PipedOutputStream();
           FFmpeg.atPath().addInput(PipeInput.pumpFrom(stream))
                   .addOutput(PipeOutput.pumpTo(image))
                   .addArguments("-ss","00:00:01.000 ")
                   .addArguments("-vframes ","1")
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
