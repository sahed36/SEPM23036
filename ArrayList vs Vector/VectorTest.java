public class VectorTest {
    import java.util.Vector;

public class VectorTest {
    public static void main(String[] args) {

        Vector<String> v = new Vector<>(10);

        for (int i = 1; i <= 10; i++) {
            v.add("IT2300" + i);
        }

        System.out.println("Size before exceeding: " + v.size());
        System.out.println("Capacity before exceeding: " + v.capacity());

        // Exceed initial capacity
        v.add("IT23011");

        System.out.println("Size after exceeding: " + v.size());
        System.out.println("Capacity after exceeding: " + v.capacity());
    }
}

    
}
