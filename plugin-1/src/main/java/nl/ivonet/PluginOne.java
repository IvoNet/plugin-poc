package nl.ivonet;

public class PluginOne extends AbstractPlugin {

    private boolean running = false;

    @Override
    public void run() {
        running = true;
        while(running) {
                System.out.println("Running two");
            try {
                Thread.sleep(500);
            } catch(InterruptedException e) {
                running = false;
            }
        }
    }

    @Override
    public void shutdown() {
        System.out.println("Shutting down two");
        this.running = false;
    }
}
