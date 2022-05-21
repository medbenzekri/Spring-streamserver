package dz.univ.bechar.mda.controller;

    import dz.univ.bechar.mda.entity.Video;
    import dz.univ.bechar.mda.service.StorageService;
    import dz.univ.bechar.mda.service.VideoService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
   private VideoService videoService;
    @Autowired
    private StorageService storageService;
    @GetMapping("/l")
    public @ResponseBody Page<Video> listvideos(Pageable pageable){

        return videoService.getVideos(pageable);
    }

    @GetMapping("/stream")
    public @ResponseBody ResponseEntity<?> streamvideo(@RequestParam String id) {

        return null;
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadvideo(@RequestParam("file")
                                  MultipartFile file ) throws Exception {
          boolean state = storageService.Store(file);
        return state? new
                ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
