package com.example.korakthoamsgabrielderdrittevontaumatawhakatangihangakoauauotamateaturipu;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WebSiteController {

    private final WebSiteService webSiteService;

    @GetMapping("/todo")
    public List<WebSiteToDo> getAllToDo() {
        return webSiteService.getAllToDo();
    }

    @GetMapping("/todo/{id}")
    public WebSiteToDo findById(@PathVariable String id) {
        return webSiteService.getToDoById(id);
    }

    @PutMapping("todo/{id}")
    public WebSiteToDo updateToDo(@PathVariable String id, @RequestBody WebSiteToDo updatedTodo) {
        return webSiteService.updateToDo(id, updatedTodo);
    }

    @PostMapping("/todo")
    public WebSiteToDo creatToDo(@RequestBody WebSiteToDo webSiteToDo) {
        return webSiteService.saveToDo(webSiteToDo);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteToDo(@PathVariable String id) {
        webSiteService.deleteToDo(id);
    }

    @GetMapping("/undo")
    public void undo() {
        webSiteService.undoLastChange();
    }

    @GetMapping("/redo")
    public void redo() {
        webSiteService.redoLastChange();
    }


}
