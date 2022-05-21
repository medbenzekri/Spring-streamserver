package dz.univ.bechar.mda.service;

import dz.univ.bechar.mda.configuration.MinioConfiguration;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class ImageService {
    @Autowired
    MinioConfiguration client;
    public ResponseEntity<?>   getthumbnail(String id){
        GetPresignedObjectUrlArgs args= GetPresignedObjectUrlArgs.builder().object(id).bucket(client.getImagebucket()).build();
        try {
          String url=  client.getPresignedObjectUrl(args);
          return ResponseEntity.status(HttpStatus.OK).location(URI.create(url)).build();
        } catch (ErrorResponseException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException |
                 IOException | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new RuntimeException(e);
        }
    }
}
