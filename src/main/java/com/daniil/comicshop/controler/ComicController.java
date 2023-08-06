package com.daniil.comicshop.controler;

import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.service.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comic")
@RequiredArgsConstructor
public class ComicController {
    private final ComicService comicService;

    @GetMapping
    public List<Comic> getAll(){
        return comicService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Comic> getComicById(@PathVariable int id){
        return comicService.getById(id);
    }

    @PostMapping("/add")
    public Comic saveComic(@RequestBody Comic comic){
        return comicService.add(comic);
    }

    @PatchMapping("/{id}/image")
    public Comic changeImg(@PathVariable int id, @RequestParam("image") MultipartFile img) throws IOException {
        return comicService.changeImg(id,img);
    }
    @PatchMapping("change")
    public Comic changeById(@RequestBody Comic comic){
        return comicService.change(comic);
    }

    @DeleteMapping("/{id}/delete")
    public Optional<Comic> deleteById(@PathVariable int id){
        return comicService.delete(id);
    }
}
