package tictactoe;

public class HumanPlayerWorker extends Thread {
    boolean moveMade = false;

    static int counter = 1;

    int instanceId;
    public HumanPlayerWorker() {
        this.instanceId = counter;
         counter++;
    }

    public void run() {
        System.out.println("Human Worker #" + instanceId + "started");

        try {
            while (moveMade == false) {
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Human Worker #" + instanceId + "finished");

    }
}
