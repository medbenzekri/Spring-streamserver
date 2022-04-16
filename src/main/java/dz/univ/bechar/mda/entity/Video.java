package dz.univ.bechar.mda.entity;

import io.minio.messages.Item;
import org.apache.commons.io.FileUtils;


public class Video {
 private    String id;
 private  final   String name;
 private    String Duration;
 private  final  String thumbnail;
 private   String Stream;
 private  final  String size;

  public Video(Item item){
     this.name= item.objectName();
     this.size=FileUtils.byteCountToDisplaySize(item.size());
     this.thumbnail=genthumbnail();
     this.Stream= "stream link" ;
    }

    private String genthumbnail(){
      return "link";
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return Duration;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getStream() {
        return Stream;
    }

    public String getSize() {
        return size;
    }
}
