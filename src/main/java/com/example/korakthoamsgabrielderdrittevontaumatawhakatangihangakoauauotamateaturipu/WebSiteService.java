package com.example.korakthoamsgabrielderdrittevontaumatawhakatangihangakoauauotamateaturipu;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class WebSiteService {

    private final WebSiteRepository webSiteRepository;
    private final List<List<WebSiteToDo>> changeHistory = new ArrayList<>();

    int index;

    public WebSiteService (WebSiteRepository webSiteRepository) {
        this.webSiteRepository = webSiteRepository;
        changeHistory.add(new ArrayList<>(webSiteRepository.findAll()));
        this.index = changeHistory.size() - 1;
    }

    public List<WebSiteToDo> getAllToDo() {
        System.out.println(changeHistory.size() + " " + index);
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

        //undoSafe
        makeChange();

        return webSiteRepository.save(updatedToDo);
    }

    public WebSiteToDo saveToDo(WebSiteToDo webSiteToDo) {
        webSiteRepository.save(webSiteToDo);

        //undoSafe
        makeChange();


        return webSiteToDo;
    }

    public void deleteToDo(String id) {
        if (!webSiteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        //undoSafe
        makeChange();

        webSiteRepository.deleteById(id);
    }

    public void undoLastChange() {
        if (changeHistory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        index -= 1;
        List<WebSiteToDo> lastChange = changeHistory.get(index);

        webSiteRepository.deleteAll();

        for (WebSiteToDo webSiteToDo : lastChange) {
            webSiteRepository.save(webSiteToDo);
        }

    }

    public void redoLastChange() {
        if (changeHistory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        index += 1;
        List<WebSiteToDo> lastChange = changeHistory.get(index);

        webSiteRepository.deleteAll();

        for (WebSiteToDo webSiteToDo : lastChange) {
            webSiteRepository.save(webSiteToDo);
        }

    }

    public void makeChange() {
        changeHistory.add(webSiteRepository.findAll());
        for (int i = changeHistory.size(); i > index; i--) {

        }
    }
}
