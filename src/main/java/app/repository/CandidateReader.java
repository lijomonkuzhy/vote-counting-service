package app.repository;

import app.model.Candidate;

import java.io.IOException;
import java.util.LinkedHashSet;

public interface CandidateReader {
     LinkedHashSet<Candidate> loadCandidateList(final String filePath) throws IOException;
}
