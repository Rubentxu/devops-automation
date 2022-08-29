package devops.automation.orchestrator.infraestructure.service

import devops.automation.orchestrator.domain.Job
import devops.automation.orchestrator.infraestructure.repository.JobRepository
import reactor.core.publisher.Flux

import org.springframework.stereotype.Service

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
