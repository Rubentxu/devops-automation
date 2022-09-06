package devops.automation.orchestrator.domain


import groovy.transform.Canonical

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
