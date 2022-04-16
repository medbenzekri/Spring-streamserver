package dz.univ.bechar.mda.controller;

    import dz.univ.bechar.mda.entity.Video;
    import dz.univ.bechar.mda.service.VideoService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
   private VideoService videoService;
    @GetMapping("/l")
    public @ResponseBody List<Video> listvideos(){

        return videoService.getVideos();
    }
}
