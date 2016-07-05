package dao;

import model.TranscriptEntry;

public interface TranscriptEntryDao {
	void addTranscriptEntry(TranscriptEntry transcriptEntry);
	void deleteTranscriptEntry(TranscriptEntry transcriptEntry);
	void updateGrade(TranscriptEntry transcriptEntry);
	TranscriptEntry searchTranscriptEntry(String SSsn, int sectionNo);
}

