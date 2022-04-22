package dz.univ.bechar.mda.controller;

import dz.univ.bechar.mda.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService service;
    @GetMapping("/thumbnail")

    public ResponseEntity<?> getthumbnail(@RequestParam String id){

        return service.getthumbnail(id);
    }
}
