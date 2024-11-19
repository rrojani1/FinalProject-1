package cyberneticFinal;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CyberneticOrgan {

    public static final Set<String> VALID_ORGAN_TYPES = new HashSet<>();

    static {
        // Initialize the set with valid organ types
        VALID_ORGAN_TYPES.add("HEART");
        VALID_ORGAN_TYPES.add("LUNG");
        VALID_ORGAN_TYPES.add("KIDNEY");
        VALID_ORGAN_TYPES.add("LIVER");

    }

    private String id;  // format: ORG-XXXX
    private String type;  // HEART, LUNG, KIDNEY, LIVER
    private String model;  // [type]X-[version]
    private int powerLevel;  // 1-100
    private double compatibilityScore;  // 0.0-1.0
    private LocalDate manufactureDate;
    private String status;  // AVAILABLE, ALLOCATED, DEFECTIVE
    private String manufacturer;


    // Constructor
    public CyberneticOrgan(String id, String type, String model, int powerLevel,
                           double compatibilityScore, LocalDate manufactureDate,
                           String status, String manufacturer) {

        // Validation
        if (!id.matches("ORG-\\d{4}")) throw new IllegalArgumentException("Organ ID format is invalid.");
        if (!type.matches("HEART|LUNG|KIDNEY|LIVER")) throw new IllegalArgumentException("Organ type is invalid.");
        if (!model.matches(type + "X-\\d+")) throw new IllegalArgumentException("Organ model format is invalid.");
        if (powerLevel < 1 || powerLevel > 100) throw new IllegalArgumentException("Organ power level is invalid.");
        if (compatibilityScore < 0.0 || compatibilityScore > 1.0) throw new IllegalArgumentException("Organ compatibility score is invalid.");
        if (manufactureDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("Manufacture date cannot be in the future.");
        if (!status.matches("AVAILABLE|ALLOCATED|DEFECTIVE")) throw new IllegalArgumentException("Invalid status.");

        this.id = id;
        this.type = type;
        this.model = model;
        this.powerLevel = powerLevel;
        this.compatibilityScore = compatibilityScore;
        this.manufactureDate = manufactureDate;
        this.status = status;
        this.manufacturer = manufacturer;
    }
}
