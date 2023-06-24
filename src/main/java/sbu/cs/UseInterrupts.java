package sbu.cs;

/*
    In this exercise, you must analyse the following code and use interrupts
    in the main function to terminate threads that run for longer than 3 seconds.

    A thread may run for longer than 3 seconds due the many different reasons,
    including lengthy process times or getting stuck in an infinite loop.

    Take note that you are NOT ALLOWED to change or delete any existing line of code.
 */

import java.security.PublicKey;

public class UseInterrupts {
    /*
        TODO
         Analyse the following class and add new code where necessary.
         If an object from this type of thread is Interrupted, it must print this:
            "{ThreadName} has been interrupted"
         And then terminate itself.
     */
    public static class SleepThread implements Runnable {
        int sleepCounter;
        boolean interrupted;

        public SleepThread(int sleepCounter) {
            super();
            this.sleepCounter = sleepCounter;
            this.interrupted = false;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is Active.");

            while (this.sleepCounter > 0 && !Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {

                } finally {
                    this.sleepCounter--;
                    System.out.println("Number of sleeps remaining: " + this.sleepCounter);
                }
            }
            System.out.println("{ " + Thread.currentThread().getName() + " }" + " has been interrupted.");
        }

    }

    /*
        TODO
         Analyse the following class and add new code where necessary.
         If an object from this type of thread is Interrupted, it must print this:
            "{ThreadName} has been interrupted"
         And then terminate itself.
         (Hint: Use the isInterrupted() method)
     */
    public static class LoopThread extends Thread {
        int value;
        boolean interrupted;
        public LoopThread(int value) {
            super();
            this.value = value;
            this.interrupted = false;
        }

        @Override
        public void run() {
            System.out.println(this.getName() + " is Active.");
            for (int i = 0; i < 10 && Thread.currentThread().isInterrupted(); i += 3) {
                i -= this.value;
            }
            System.out.println("{ " + Thread.currentThread().getName() + " }" + " has been interrupted.");
        }
        @Override
        public void interrupt(){
            this.interrupted = !this.interrupted;
        }
        @Override
        public boolean isInterrupted(){
            return this.interrupted;
        }
    }

    /*
        You can add new code to the main function. This is where you must utilize interrupts.
        No existing line of code should be changed or deleted.
     */
    public static void main(String[] args) {

        // TODO  Check if this thread runs for longer than 3 seconds (if it does, interrupt it)
        SleepThread sleepThread = new SleepThread(5);
        long now = System.currentTimeMillis();
        Thread thread = new Thread(sleepThread);
        thread.start();
        while(!thread.isInterrupted()){
            long subtract = System.currentTimeMillis() - now;
            if(subtract > 3000){
                thread.interrupt();
                break;
            }
        }

        // TODO  Check if this thread runs for longer than 3 seconds (if it does, interrupt it)

    }
}