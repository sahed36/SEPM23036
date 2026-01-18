public class ParkingDemo {
    import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Represents a car parking request
class RegistrarParking {
    private static int counter = 1; // Static counter for car IDs
    private int carId;

    public RegistrarParking() {
        this.carId = counter++;
    }

    public int getCarId() {
        return carId;
    }

    @Override
    public String toString() {
        return "Car-" + carId;
    }
}

// Queue to hold cars waiting to be parked
class ParkingPool {
    private Queue<RegistrarParking> queue = new LinkedList<>();

    // Add car to queue (thread-safe)
    public synchronized void addCar(RegistrarParking car) {
        queue.add(car);
        System.out.println(car + " is waiting to be parked.");
        notifyAll(); // Notify waiting parking agents
    }

    // Get car from queue (wait if empty)
    public synchronized RegistrarParking getCar() {
        while (queue.isEmpty()) {
            try {
                wait(); // Wait until a car is added
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.poll();
    }
}

// Thread that parks cars from the ParkingPool
class ParkingAgent extends Thread {
    private ParkingPool pool;

    public ParkingAgent(String name, ParkingPool pool) {
        super(name);
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true) {
            RegistrarParking car = pool.getCar();
            System.out.println(getName() + " is parking " + car);
            try {
                Thread.sleep(1000); // Simulate parking time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " finished parking " + car);
        }
    }
}

// Main class to simulate cars arriving and being parked
public class ParkingDemo {
    public static void main(String[] args) {
        ParkingPool pool = new ParkingPool();

        // Create 3 Parking Agents
        new ParkingAgent("Agent-1", pool).start();
        new ParkingAgent("Agent-2", pool).start();
        new ParkingAgent("Agent-3", pool).start();

        Scanner scanner = new Scanner(System.in);
        int carCount = 0;
        int totalCars = 10; // Total number of cars to simulate

        while (carCount < totalCars) {
            System.out.println("Press Enter to send a new car to parking (or type -1 to exit)");
            String input = scanner.nextLine();
            if (input.equals("-1")) break;

            RegistrarParking car = new RegistrarParking();
            pool.addCar(car);
            carCount++;
        }

        scanner.close();
    }
}

    
}
