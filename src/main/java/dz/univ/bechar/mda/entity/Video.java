package dz.univ.bechar.mda.entity;
import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

@Entity
public class Video {


    @Id
    @GeneratedValue()
    private    Long id;

    private String code;
    private String name;
    private Duration Duration;
     private URI thumbnail;
     private  URI Stream;
     private    String size;

    public String getCode() {
        return code;
    }

    public Video setCode(String code) {
        this.code = code;
        return this;
    }

    public Video setName(String name) {
        this.name = name;
        return this;
    }

    public Video setDuration(Duration duration) {
        Duration = duration;
        return this;
    }

    public Video setThumbnail(URI thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public Video setStream(URI stream) {
        Stream = stream;
        return this;
    }

    public Video setSize(String size) {
        this.size = size;
        return this;
    }


    public Video(){}




    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Duration getDuration() {
        return Duration;
    }

    public String getThumbnail() throws MalformedURLException {
        return thumbnail.getHost();
    }

    public String getStream() {
        return Stream.toString();
    }

    public String getSize() {
        return size;
    }
}
