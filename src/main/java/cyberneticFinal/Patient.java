package cyberneticFinal;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Patient {
    private String id;  // PAT-XXXX
    private String name;
    private int age;  // 1-120
    private String bloodType;  // A+, A-, B+, B-, AB+, AB-, O+, O-
    private String organNeeded;  // HEART, LUNG, KIDNEY, LIVER
    private int urgencyLevel;  // 1-10
    private LocalDate registrationDate;
    private String status;  // WAITING, MATCHED, TRANSPLANTED

    public Patient(String id, String name, int age, String bloodType,
                   String organNeeded, int urgencyLevel, LocalDate registrationDate, String status) {
        // Validation
        if (!id.matches("PAT-\\d{3}")) throw new IllegalArgumentException("Invalid ID format");
        if (age < 1 || age > 120) throw new IllegalArgumentException("Patient age is invalid.");
        if (!bloodType.matches("A[+-]|B[+-]|AB[+-]|O[+-]")) throw new IllegalArgumentException("Patient blood type is invalid.");
        if (!organNeeded.matches("HEART|LUNG|KIDNEY|LIVER")) throw new IllegalArgumentException("Invalid organ type");
        if (urgencyLevel < 1 || urgencyLevel > 10) throw new IllegalArgumentException("Urgency level is invalid.");
        if (!status.equals("WAITING")) throw new IllegalArgumentException("New patient status must be WAITING.");

        this.id = id;
        this.name = name;
        this.age = age;
        this.bloodType = bloodType;
        this.organNeeded = organNeeded;
        this.urgencyLevel = urgencyLevel;
        this.registrationDate = registrationDate;
        this.status = status;
    }

}
