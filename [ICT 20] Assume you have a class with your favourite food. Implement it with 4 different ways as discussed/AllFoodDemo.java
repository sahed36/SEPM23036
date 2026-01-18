public class AllFoodDemo {
    // 1) Simple Class with Method
class Food1 {
    void show() {
        System.out.println("My favourite food is Pizza. (Method)");
    }
}

// 2) Using Constructor
class Food2 {
    Food2() {
        System.out.println("My favourite food is Pizza. (Constructor)");
    }
}

// 3) Using Inheritance
class FoodBase {
    void show() {
        System.out.println("My favourite food is Pizza. (Inheritance)");
    }
}

class Food3 extends FoodBase {
}

// 4) Using Interface
interface FoodInterface {
    void show();
}

class Food4 implements FoodInterface {
    public void show() {
        System.out.println("My favourite food is Pizza. (Interface)");
    }
}

// Main Class to Run All
public class AllFoodDemo {
    public static void main(String[] args) {

        // Method
        Food1 f1 = new Food1();
        f1.show();

        // Constructor
        new Food2();

        // Inheritance
        Food3 f3 = new Food3();
        f3.show();

        // Interface
        Food4 f4 = new Food4();
        f4.show();
    }
}

    
}
