public class arraylisttest {import java.lang.reflect.Field;
    import java.util.ArrayList;
    
    public class ArrayListTest {
        public static void main(String[] args) throws Exception {
    
            ArrayList<String> list = new ArrayList<>(10);
    
            for (int i = 1; i <= 10; i++) {
                list.add("IT2300" + i);
            }
    
            System.out.println("Size before exceeding: " + list.size());
            System.out.println("Capacity before exceeding: " + getCapacity(list));
    
            // Exceed initial capacity
            list.add("IT23011");
    
            System.out.println("Size after exceeding: " + list.size());
            System.out.println("Capacity after exceeding: " + getCapacity(list));
        }
    
        // Method to get internal capacity using reflection
        static int getCapacity(ArrayList<?> list) throws Exception {
            Field field = ArrayList.class.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] data = (Object[]) field.get(list);
            return data.length;
        }
    }
    
    
}
