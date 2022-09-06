package devops.automation.orchestrator.infraestructure.service

import devops.automation.orchestrator.domain.Job
import devops.automation.orchestrator.infraestructure.persistence.repositories.JobRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class JobService {
    private JobRepository repository

    public JobService(JobRepository repository) {
        this.repository = repository
    }

    public Flux<Job> getByAuthor(String author) {
        return repository.getByAuthor(author)
    }
}
