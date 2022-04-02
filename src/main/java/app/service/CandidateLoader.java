package app.service;

import app.model.Candidate;
import app.repository.CandidateFileReader;

import java.io.IOException;
import java.util.Set;

public class CandidateLoader {

    final private CandidateFileReader candidateFileReader;

    public CandidateLoader(final CandidateFileReader candidateFileReader) {
        this.candidateFileReader = candidateFileReader;
    }

    public Set<Candidate> loadCandidateList() throws IOException {
        return null;
    }
}
