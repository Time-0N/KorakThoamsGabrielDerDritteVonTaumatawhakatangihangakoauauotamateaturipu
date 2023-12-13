package com.example.korakthoamsgabrielderdrittevontaumatawhakatangihangakoauauotamateaturipu;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebSiteRepository extends MongoRepository<WebSiteToDo, String> {
}
