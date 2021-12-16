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
            //Thread.sleep(3000);
            while (moveMade == false) {
                Thread.sleep(15);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Human Worker #" + instanceId + "finished");

    }
}
