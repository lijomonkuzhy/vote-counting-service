package app.repository;

import app.model.Candidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CandidateFileReaderTest {
    private CandidateFileReader candidateFileReader;

    @BeforeEach
    public void setUp() {
        candidateFileReader = new CandidateFileReader();
    }

    @Test
    public void testLoadCandidateListSuccess() throws IOException {
        final String filePath = "src/test/resources/candidates_valid.txt";

        assertThat(candidateFileReader.loadCandidateList(filePath))
                .isEqualTo(validCandidateList());
    }

    @Test
    public void testLoadCandidateListWithBlankLine() throws IOException {
        final String filePath = "src/test/resources/candidates_with_blank_lines.txt";

        assertThat(candidateFileReader.loadCandidateList(filePath))
                .isEqualTo(validCandidateList());
    }

    @Test
    public void testLoadCandidateListWithWhiteSpacesAtTheBeginningAndEnd() throws IOException {
        final String filePath = "src/test/resources/candidates_with_white_spaces.txt";

        assertThat(candidateFileReader.loadCandidateList(filePath))
                .isEqualTo(validCandidateList());

        System.out.println(validCandidateList());
    }

    private LinkedHashSet<Candidate> validCandidateList() throws IOException {

        return Stream.of(Candidate.newBuilder().withName("Winery tour").withOption('A').build(),
                Candidate.newBuilder().withName("Ten pin bowling").withOption('B').build(),
                Candidate.newBuilder().withName("Movie night").withOption('C').build(),
                Candidate.newBuilder().withName("Dinner at a restaurant").withOption('D').build(),
                Candidate.newBuilder().withName("Art gallery visit").withOption('E').build(),
                Candidate.newBuilder().withName("Picnic in the park").withOption('F').build(),
                Candidate.newBuilder().withName("Horse riding lessons").withOption('G').build(),
                Candidate.newBuilder().withName("Museum visit").withOption('H').build(),
                Candidate.newBuilder().withName("Surfing lesson").withOption('I').build())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}