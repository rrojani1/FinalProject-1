package cyberneticFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Comparator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrganInventory {
    private ArrayList<CyberneticOrgan> organs = new ArrayList<>();
    private final int maxCapacity = 1000;

    public boolean addOrgan(CyberneticOrgan organ) {
        if (organs.size() >= maxCapacity) {
            System.out.println("Inventory full. Cannot add organ.");
            return false;
        }

        if (isValidOrgan(organ)) {
            organs.add(organ);
            return true;
        }
        return false;
    }

    private boolean isValidOrgan(CyberneticOrgan organ) {
        if (organ.getPowerLevel() < 1 || organ.getPowerLevel() > 100) {
            System.out.println("Invalid power level: " + organ.getPowerLevel() + " for organ ID " + organ.getId());
            return false;
        }
        if (organ.getManufactureDate().isAfter(LocalDate.now())) {
            System.out.println("Manufacture date " + organ.getManufactureDate() + " is in the future for organ ID " + organ.getId());
            return false;
        }
        if (!CyberneticOrgan.VALID_ORGAN_TYPES.contains(organ.getType())) {
            System.out.println("Invalid organ type: " + organ.getType() + " for organ ID " + organ.getId());
            return false;
        }
        if (organs.stream().anyMatch(o -> o.getId().equals(organ.getId()))) {
            System.out.println("Duplicate organ ID: " + organ.getId());
            return false;
        }
        return true;
    }


    public boolean removeOrgan(String id, String reason) {
        for (CyberneticOrgan organ : organs) {
            if (organ.getId().equals(id) && organ.getStatus().equals("AVAILABLE")) {
                organs.remove(organ);
                System.out.println("Organ removed. Reason: " + reason);
                return true;
            }
        }
        System.out.println("Organ not available for removal.");
        return false;
    }

    public ArrayList<CyberneticOrgan> sortByPowerLevel() {
        return quickSort(new ArrayList<>(organs), 0, organs.size() - 1);
    }

    private ArrayList<CyberneticOrgan> quickSort(ArrayList<CyberneticOrgan> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
        return list;
    }

    private int partition(ArrayList<CyberneticOrgan> list, int low, int high) {
        int pivot = list.get(high).getPowerLevel();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).getPowerLevel() > pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    public ArrayList<CyberneticOrgan> sortByManufactureDate() {
        ArrayList<CyberneticOrgan> sortedList = new ArrayList<>(organs);
        mergeSort(sortedList, 0, sortedList.size() - 1);
        return sortedList;
    }

    private void mergeSort(ArrayList<CyberneticOrgan> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    private void merge(ArrayList<CyberneticOrgan> list, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        ArrayList<CyberneticOrgan> leftList = new ArrayList<>(list.subList(left, mid + 1));
        ArrayList<CyberneticOrgan> rightList = new ArrayList<>(list.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftList.get(i).getManufactureDate().isAfter(rightList.get(j).getManufactureDate())) {
                list.set(k++, leftList.get(i++));
            } else {
                list.set(k++, rightList.get(j++));
            }
        }

        while (i < n1) {
            list.set(k++, leftList.get(i++));
        }

        while (j < n2) {
            list.set(k++, rightList.get(j++));
        }
    }

    public ArrayList<CyberneticOrgan> sortByCompatibilityScore() {
        ArrayList<CyberneticOrgan> sortedList = new ArrayList<>(organs);
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < sortedList.size() - 1; i++) {
                if (sortedList.get(i).getCompatibilityScore() < sortedList.get(i + 1).getCompatibilityScore()) {
                    Collections.swap(sortedList, i, i + 1);
                    swapped = true;
                }
            }
        } while (swapped);
        return sortedList;
    }
    // Returns the list of organs in the inventory
    public ArrayList<CyberneticOrgan> getOrgans() {
        return organs;
    }

    // Returns the maximum capacity of the inventory
    public int getMaxCapacity() {
        return maxCapacity;
    }
}

