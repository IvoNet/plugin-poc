package nl.ivonet;

public class PluginThree extends AbstractPlugin {

    private boolean running = false;

    @Override
    public void run() {
        running = true;
        while(running) {
                System.out.println("Running Three");
            try {
                Thread.sleep(300);
            } catch(InterruptedException e) {
                running = false;
            }
        }
    }

    @Override
    public void shutdown() {
        System.out.println("Shutting down Three");
        this.running = false;
    }
}
