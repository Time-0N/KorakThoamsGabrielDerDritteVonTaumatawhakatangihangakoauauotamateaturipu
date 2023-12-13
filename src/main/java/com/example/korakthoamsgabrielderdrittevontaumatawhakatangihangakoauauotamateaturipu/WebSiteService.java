package com.example.korakthoamsgabrielderdrittevontaumatawhakatangihangakoauauotamateaturipu;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WebSiteService {

    private final WebSiteRepository webSiteRepository;

    public List<WebSiteToDo> getAllToDo() {
        return webSiteRepository.findAll();
    }

    public WebSiteToDo getToDoById(String id) {
        return webSiteRepository.findById(id).orElse(null);
    }

    public WebSiteToDo updateToDo(String id, WebSiteToDo updatedToDo) {
        WebSiteToDo toDoToUpdate = getToDoById(id);

        if (toDoToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!Objects.equals(id, updatedToDo.id())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return webSiteRepository.save(updatedToDo);
    }

    public WebSiteToDo saveToDo(WebSiteToDo webSiteToDo) {
        webSiteRepository.save(webSiteToDo);
        return webSiteToDo;
    }

    public void deleteToDo(String id) {
        if (!webSiteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        webSiteRepository.deleteById(id);
    }

}
