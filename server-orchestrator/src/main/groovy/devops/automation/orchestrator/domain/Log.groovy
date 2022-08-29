package devops.automation.orchestrator.domain

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import groovy.transform.Canonical
import io.netty.util.internal.ThreadLocalRandom

import java.time.LocalDate

@Canonical
class Log {
    List<String> lines = []
    int bufferNumLines = 8

    void addLine(String line) {
        lines.add(line)
    }

    Boolean isOverflowLines() {
        lines.size() > bufferNumLines
    }

    void resetLines() {
        lines = []
    }




}
