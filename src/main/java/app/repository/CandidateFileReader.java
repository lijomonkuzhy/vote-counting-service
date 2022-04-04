package app.repository;

import app.model.Candidate;
import app.util.validator.StringValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class CandidateFileReader {

    public LinkedHashSet<Candidate> loadCandidateList(final String filePath) throws IOException {
        final char[] options = {'A'};
        return Files.lines(Path.of(filePath))
                .filter(line -> !StringValidator.isNullOrEmpty(line))
                .map(entry -> Candidate.newBuilder().withName(entry).withOption(options[0]++).build())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
