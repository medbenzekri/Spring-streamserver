package dz.univ.bechar.mda.service;

import dz.univ.bechar.mda.configuration.MinioConfiguration;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class UploadService {
    @Autowired
    private MinioConfiguration client;

    public void uploadthumbnail(Path image, String id ){
        try {
            client.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(client.getImagebucket())
                            .object(id)
                            .filename(image.toString())
                            .contentType("image/jpeg")
                            .build());

        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 IOException | NoSuchAlgorithmException | ServerException | XmlParserException |
                 InvalidResponseException e) {
            throw new RuntimeException(e);
        }


    }
    public void uploadvideo(MultipartFile file){
        try {
            client.putObject(
                    PutObjectArgs.builder()
                            .bucket(client.getVideobucket())
                            .object(file.getName())
                            .stream(file.getInputStream(),file.getSize(),-1)
                            .contentType(file.getContentType())
                            .build());


        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 IOException | NoSuchAlgorithmException | ServerException | XmlParserException |
                 InvalidResponseException e) {
            throw new RuntimeException(e);
        }

    }
}
