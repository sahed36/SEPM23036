public class CollectionDemo {
    import java.util.*;

// (1) Kth Smallest Element
class KthSmallest {
    static void findKth() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(40, 10, 30, 20, 50));
        int k = 3;
        Collections.sort(list);
        System.out.println("Kth Smallest Element (" + k + "): " + list.get(k - 1));
    }
}

// (2) Word Frequency using TreeMap
class WordFrequency {
    static void countWords() {
        String text = "java is easy and java is powerful";
        String[] words = text.split(" ");
        TreeMap<String, Integer> map = new TreeMap<>();
        for (String w : words)
            map.put(w, map.getOrDefault(w, 0) + 1);
        System.out.println("Word Frequencies: " + map);
    }
}

// (3) Stack & Queue using PriorityQueue
class PQStackQueue {
    static void demonstrate() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.naturalOrder());
        queue.addAll(Arrays.asList(10, 20, 30));
        System.out.print("Queue (FIFO): ");
        while (!queue.isEmpty()) System.out.print(queue.poll() + " ");
        System.out.println();

        PriorityQueue<Integer> stack = new PriorityQueue<>(Comparator.reverseOrder());
        stack.addAll(Arrays.asList(10, 20, 30));
        System.out.print("Stack (LIFO): ");
        while (!stack.isEmpty()) System.out.print(stack.poll() + " ");
        System.out.println();
    }
}

// (4) Student TreeMap
class Student {
    String name; double cgpa;
    Student(String name, double cgpa) { this.name = name; this.cgpa = cgpa; }
    public String toString() { return name + "(CGPA:" + cgpa + ")"; }
}
class StudentTreeMap {
    static void showStudents() {
        TreeMap<Integer, Student> students = new TreeMap<>();
        students.put(101, new Student("Akib", 3.75));
        students.put(103, new Student("Rafi", 3.60));
        students.put(102, new Student("Nabil", 3.90));
        System.out.println("Students: " + students);
    }
}

// (5) LinkedList Equality
class LinkedListEqual {
    static void checkEquality() {
        LinkedList<Integer> l1 = new LinkedList<>(Arrays.asList(1,2,3));
        LinkedList<Integer> l2 = new LinkedList<>(Arrays.asList(1,2,3));
        System.out.println("LinkedLists equal? " + l1.equals(l2));
    }
}

// (6) Employee HashMap
class EmployeeHashMap {
    static void showEmployees() {
        HashMap<Integer, String> emp = new HashMap<>();
        emp.put(1, "IT"); emp.put(2, "HR"); emp.put(3, "Finance");
        System.out.println("Employee Departments: " + emp);
    }
}

// Main class (public, matches file name)
public class CollectionDemo {
    public static void main(String[] args) {
        System.out.println("---- Kth Smallest ----");
        KthSmallest.findKth();

        System.out.println("\n---- Word Frequency ----");
        WordFrequency.countWords();

        System.out.println("\n---- PriorityQueue as Stack & Queue ----");
        PQStackQueue.demonstrate();

        System.out.println("\n---- Students TreeMap ----");
        StudentTreeMap.showStudents();

        System.out.println("\n---- LinkedList Equality ----");
        LinkedListEqual.checkEquality();

        System.out.println("\n---- Employee HashMap ----");
        EmployeeHashMap.showEmployees();
    }
}

    
}
