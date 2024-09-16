package advanced_concepts.clocks;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        Thread.sleep(1000);
        long end = System.nanoTime();

        long duration = (end - start) / 1_000_000;
        System.out.println("Finished in: " + duration + " nanoseconds.");
    }
}
