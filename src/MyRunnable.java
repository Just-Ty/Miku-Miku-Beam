public class MyRunnable implements Runnable {
    @Override
    public void run() {
        try{
            Thread.sleep(9900);
            System.out.println("Timer has ended");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }
}
