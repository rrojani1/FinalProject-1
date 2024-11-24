package cyberneticFinal;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDate;


@Getter
@Setter
public class EmergencyCase {
    private String caseId;
    private Patient patient;
    private int severityLevel; // 1-5
    private LocalDateTime registrationTime;

    public EmergencyCase(String caseId, Patient patient, int severityLevel, LocalDateTime registrationTime) {
        this.caseId = caseId;
        this.patient = patient;
        this.severityLevel = severityLevel;
        this.registrationTime = registrationTime;
    }

    @Override
    public String toString() {
        long minutes = java.time.Duration.between(registrationTime, LocalDateTime.now()).toMinutes();
        return caseId + " (Severity: " + severityLevel +
                ", Wait time: " + minutes + " min)";
    }
}
