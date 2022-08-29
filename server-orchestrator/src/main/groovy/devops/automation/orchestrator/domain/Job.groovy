package devops.automation.orchestrator.domain

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import groovy.transform.Canonical
import groovy.transform.Immutable
import io.netty.util.internal.ThreadLocalRandom

import java.time.LocalDate

@Canonical
class Job {
    String id
    String author
    String name
    String pipeline
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
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
