package cyberneticFinal;


import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SystemOperation {
    private String operationId;
    private String operationType; // "MATCH", "TRANSPLANT", "EMERGENCY"
    private LocalDateTime timestamp;
    private String description;
    private boolean isReversible;

    public SystemOperation(String operationId, String operationType, String description, boolean isReversible) {
        this.operationId = operationId;
        this.operationType = operationType;
        this.description = description;
        this.isReversible = isReversible;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return operationId + ": " + operationType;
//    @Override
//    public String toString() {
//        return operationId + ": " + operationType + " (" + description + ")";
    }
}

// SystemOperationsLog class (Stack)
class SystemOperationsLog {
    private SystemOperation[] stack;
    private int top;

    // Constructor
    public SystemOperationsLog(int size) {
        this.stack = new SystemOperation[size];
        this.top = -1; // empty stack
    }

    // Push a new operation
    public void pushOperation(SystemOperation operation) {
        if (top < stack.length - 1) {
            stack[++top] = operation;
        } else {
            System.out.println("Stack Overflow: Cannot push operation.");
        }
    }
    // Pop last operation
    public SystemOperation popLastOperation() {
        if (top >= 0) {
            return stack[top--];
        } else {
            System.out.println("Stack Underflow: No operation to pop.");
            return null;
        }
    }
    // Peek at the last operation without removing it
    public SystemOperation peekLastOperation() {
        if (top >= 0) {
            return stack[top];
        } else {
            System.out.println("Stack is empty: No operation to peek.");
            return null;
        }
    }

    // Undo last operation if reversible
    public void undoLastOperation() {
        if (top == -1) {
            System.out.println("No operation to undo.");
            return;
        }
        if (stack[top].isReversible()) {
            System.out.println("Undoing operation: " + stack[top]);
            popLastOperation();
        } else {
            System.out.println("Operation not reversible: " + stack[top]);
        }
    }

    // Get recent operations
    public List<SystemOperation> getRecentOperations(int count) {
        List<SystemOperation> recentOperations = new ArrayList<>();
        for (int i = top; i >= 0 && count > 0; i--, count--) {
            recentOperations.add(stack[i]);
        }
        return recentOperations;
    }
}

    // Display all stack contents
//    public void displayStackContents() {
//        if (top == -1) {
//            System.out.println("Stack is empty.");
//            break;
//        }
//        System.out.println("All Operations in Stack:");
//        for (int i = 0; i <= top; i++) {
//            System.out.println(stack[i]);
//        }

