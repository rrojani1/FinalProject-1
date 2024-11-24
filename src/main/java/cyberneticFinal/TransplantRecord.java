package cyberneticFinal;


import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TransplantRecord {

    private String operationId;
    private String patientId;
    private String organId;
    private LocalDateTime timestamp;
    private String surgeon;
    private String outcome;
    TransplantRecord next; // for LinkedList implementation

    public TransplantRecord(String operationId, String patientId, String organId, String surgeon, String outcome) {
        this.operationId = operationId;
        this.patientId = patientId;
        this.organId = organId;
        this.surgeon = surgeon;
        this.outcome = outcome;
        this.timestamp = LocalDateTime.now();
        this.next = null;
    }
    @Override
    public String toString() {
        return operationId + ": " + patientId + " (" + outcome + ")";
    }
}

// TransplantHistory class (LinkedList)
class TransplantHistory {
    private TransplantRecord head;

    public TransplantHistory() {
        this.head = null;
    }

    public void addTransplantRecordAtBeginning(TransplantRecord record) {
        record.setNext(head);
        head = record;
    }

    // Find transplant record by patient ID
    public TransplantRecord findTransplantByPatient(String patientId) {
        TransplantRecord current = head;
        while (current != null) {
            if (current.getPatientId().equals(patientId)) {
                return current; // Found the transplant record
            }
            current = current.getNext();
        }
        return null; // Patient not found
    }

    // Get the most recent transplant records (by the count specified)
    public List<TransplantRecord> getRecentTransplants(int count) {
        List<TransplantRecord> recentTransplants = new ArrayList<>();
        TransplantRecord current = head;
        int counter = 0;

        while (current != null && counter < count) {
            recentTransplants.add(current);
            current = current.getNext();
            counter++;
        }

        return recentTransplants;
    }

    // Get all transplants by date
    public List<TransplantRecord> getAllTransplantsByDate(LocalDate date) {
        List<TransplantRecord> transplantsByDate = new ArrayList<>();
        TransplantRecord current = head;

        while (current != null) {
            if (current.getTimestamp().toLocalDate().equals(date)) {
                transplantsByDate.add(current);
            }
            current = current.getNext();
        }

        return transplantsByDate;
    }
}