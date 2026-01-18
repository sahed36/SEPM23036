public class OrderApp {
    class PlaceOrder extends Thread {
        public void run() {
            try {
                System.out.println("Placing order...");
                Thread.sleep(2000); // সময় লাগছে অর্ডার দিতে
                System.out.println("Order placed successfully!");
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
    
    class DeliverOrder extends Thread {
        public void run() {
            try {
                System.out.println("Preparing food...");
                Thread.sleep(3000); // রান্নার সময়
                System.out.println("Food is on the way...");
                Thread.sleep(2000); // ডেলিভারির সময়
                System.out.println("Order delivered!");
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
    
    public class OrderApp {
        public static void main(String[] args) {
    
            PlaceOrder p = new PlaceOrder();
            DeliverOrder d = new DeliverOrder();
    
            p.start();   // Thread 1: Place order
            d.start();   // Thread 2: Deliver order
        }
    }
    
    
}
