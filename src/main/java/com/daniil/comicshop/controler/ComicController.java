package com.daniil.comicshop.controler;

import com.daniil.comicshop.entity.dto.request.ComicRequest;
import com.daniil.comicshop.entity.dto.response.ComicResponse;
import com.daniil.comicshop.service.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/comic")
@RequiredArgsConstructor
public class ComicController {
    private final ComicService comicService;

    @GetMapping
    public List<ComicResponse> getAll(){
        return comicService.getAllComic();
    }

    @GetMapping("/{id}")
    public ComicResponse getComicById(@PathVariable int id){
        return comicService.getComicById(id);
    }

    @PostMapping("/add")
    public ComicResponse saveComic(@RequestBody ComicRequest comicRequest){
        return comicService.saveComic(comicRequest);
    }

    @PutMapping("/{id}/image")
    public ComicResponse changeImg(@PathVariable int id, @RequestParam("image") MultipartFile img) throws IOException {
        return comicService.changeImg(id,img);
    }
    @PutMapping("/{id}/change")
    public ComicResponse changeById(@PathVariable int id,@RequestBody ComicRequest comicRequest){
        return comicService.changeById(id,comicRequest);
    }

    @DeleteMapping("/{id}/delete")
    public String deleteById(@PathVariable int id){
        return comicService.deleteById(id);
    }

}
