package devops.automation.orchestrator.domain


import groovy.transform.Canonical

import java.time.LocalDate
import java.util.concurrent.ThreadLocalRandom

@Canonical
class Job {
    String id
    String author
    String name
    String pipeline
    LocalDate date

    Job() {
    }

    Job(String author, String name, String pipeline) {
        this.id = UUID.randomUUID().toString()
        this.author = author
        this.name = name
        this.pipeline = pipeline
        this.date = getRandomDate()
    }

    private LocalDate getRandomDate() {
        ThreadLocalRandom r = ThreadLocalRandom.current()
        return LocalDate.of(r.nextInt(1990, 2020), r.nextInt(1, 13), r.nextInt(1, 29))
    }
}
