package devops.automation.orchestrator.infraestructure.persistence.repositories


import devops.automation.orchestrator.domain.Job
import groovy.transform.CompileStatic
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

import java.util.concurrent.ConcurrentHashMap

@CompileStatic
@Repository
class JobRepository {
    private final Map<String, Job> store

    JobRepository() {
        this.store = new ConcurrentHashMap<>()
    }

    Mono<Boolean> save(Job job) {
        println("Store Job : $job")
        Mono.just(store.put(job.getId(), job)? true:false)
    }

    Flux<Job> getByAuthor(String author) {
        def jobs = store.findAll { key, job -> job.author == author }.collect { it.value } as List
        println("Get by Autor $author: $jobs")
        return Flux.fromIterable(jobs)
    }
}
