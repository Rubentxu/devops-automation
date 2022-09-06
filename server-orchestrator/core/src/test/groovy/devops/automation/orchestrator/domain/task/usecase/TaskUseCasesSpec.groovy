package devops.automation.orchestrator.domain.task.usecase

import devops.automation.orchestrator.domain.shared.ID
import devops.automation.orchestrator.domain.task.ports.TaskRepository
import spock.lang.Specification

class TaskUseCasesSpec extends Specification {
    void setup() {
    }

    def "GetTask"() {
        given:
        TaskUseCases useCases = new TaskUseCases(Mock(TaskRepository.class))

        when:
        def task = useCases.getTask(new ID('SDFASDF'))

        then:
        task != null
    }

    def "GetAllByUser"() {
    }

    def "GetAll"() {
    }

    def "Save"() {
    }
}
