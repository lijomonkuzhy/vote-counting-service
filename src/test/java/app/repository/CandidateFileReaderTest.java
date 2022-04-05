package app.repository;

import app.model.Candidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashSet;

import static app.util.CandidateProvider.provideValidCandidates;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CandidateFileReaderTest {
    private static LinkedHashSet<Candidate> VALID_CANDIDATES = provideValidCandidates();
    private CandidateFileReader candidateFileReader;

    @BeforeEach
    public void setUp() {
        candidateFileReader = new CandidateFileReader();
    }

    @Test
    public void testLoadCandidateListSuccess() throws IOException {
        final String filePath = "src/test/resources/candidates_valid.txt";

        assertThat(candidateFileReader.loadCandidateList(filePath))
                .isEqualTo(VALID_CANDIDATES);
    }

    @Test
    public void testLoadCandidateListWithBlankLine() throws IOException {
        final String filePath = "src/test/resources/candidates_with_blank_lines.txt";

        assertThat(candidateFileReader.loadCandidateList(filePath))
                .isEqualTo(VALID_CANDIDATES);
    }

    @Test
    public void testLoadCandidateListWithWhiteSpacesAtTheBeginningAndEnd() throws IOException {
        final String filePath = "src/test/resources/candidates_with_white_spaces.txt";

        assertThat(candidateFileReader.loadCandidateList(filePath))
                .isEqualTo(VALID_CANDIDATES);
    }

}