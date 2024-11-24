package cyberneticFinal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.PriorityQueue;
import lombok.Getter;
import lombok.Setter;
//import lombok.ToString;

@Getter
@Setter
//@ToString(exclude = {"left", "right"})

public class EmergencyWaitlist {
    private PriorityQueue<EmergencyCase> priorityQueue;
    private BSTNode root; // BST for searchable index

    public EmergencyWaitlist() {
        this.priorityQueue = new PriorityQueue<>(Comparator
                .comparingInt(EmergencyCase::getSeverityLevel).reversed()
                .thenComparing(EmergencyCase::getRegistrationTime));
    }

    // Add emergency case
    public void addEmergencyCase(EmergencyCase emergencyCase) {
        priorityQueue.add(emergencyCase);
        root = addToBST(root, emergencyCase);
    }

    // Retrieve and remove the next urgent case
    public EmergencyCase getNextUrgentCase() {
        EmergencyCase urgentCase = priorityQueue.poll();
        if (urgentCase != null) {
            root = removeFromBST(root, urgentCase.getCaseId());
        }
        return urgentCase;
    }

    // Update the severity of an emergency case
    public void updateCaseSeverity(String caseId, int newLevel) {
        EmergencyCase caseToUpdate = findCaseById(caseId);
        if (caseToUpdate != null) {
            priorityQueue.remove(caseToUpdate);
            caseToUpdate.setSeverityLevel(newLevel);
            priorityQueue.add(caseToUpdate);
        }
    }

    // Find a case by its ID using the BST
    public EmergencyCase findCaseById(String caseId) {
        return searchBST(root, caseId);
    }

    // Retrieve all cases by severity level
    public void getAllCasesBySeverity(int level) {
        getAllCasesBySeverity(root, level);
    }

    // Add case to BST
    private BSTNode addToBST(BSTNode root, EmergencyCase emergencyCase) {
        if (root == null) {
            return new BSTNode(emergencyCase);
        }
        if (emergencyCase.getCaseId().compareTo(root.getData().getCaseId()) < 0) {
            root.setLeft(addToBST(root.getLeft(), emergencyCase));
        } else {
            root.setRight(addToBST(root.getRight(), emergencyCase));
        }
        return root;
    }

    // Remove case from BST
    private BSTNode removeFromBST(BSTNode root, String caseId) {
        if (root == null) return null;
        if (caseId.compareTo(root.getData().getCaseId()) < 0) {
            root.setLeft(removeFromBST(root.getLeft(), caseId));
        } else if (caseId.compareTo(root.getData().getCaseId()) > 0) {
            root.setRight(removeFromBST(root.getRight(), caseId));
        } else {
            if (root.getLeft() == null) return root.getRight();
            if (root.getRight() == null) return root.getLeft();
            BSTNode successor = findMin(root.getRight());
            root.setData(successor.getData());
            root.setRight(removeFromBST(root.getRight(), successor.getData().getCaseId()));
        }
        return root;
    }

    // Search BST for a case by ID
    private EmergencyCase searchBST(BSTNode root, String caseId) {
        if (root == null) return null;
        if (caseId.equals(root.getData().getCaseId())) return root.getData();
        if (caseId.compareTo(root.getData().getCaseId()) < 0) {
            return searchBST(root.getLeft(), caseId);
        } else {
            return searchBST(root.getRight(), caseId);
        }
    }

    // Helper: Get all cases by severity
    private void getAllCasesBySeverity(BSTNode root, int severity) {
        if (root == null) return;
        if (root.getData().getSeverityLevel() == severity) {
            System.out.println(root.getData());
        }
        getAllCasesBySeverity(root.getLeft(), severity);
        getAllCasesBySeverity(root.getRight(), severity);
    }

    // Helper: Find minimum node in BST
    private BSTNode findMin(BSTNode root) {
        while (root.getLeft() != null) root = root.getLeft();
        return root;
    }
}

// BST Node Class
class BSTNode {
    private EmergencyCase data;
    private BSTNode left, right;

    public BSTNode(EmergencyCase data) {
        this.data = data;
    }

    public EmergencyCase getData() {
        return data;
    }

    public void setData(EmergencyCase data) {
        this.data = data;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }
}
