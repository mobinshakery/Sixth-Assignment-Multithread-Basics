package sbu.cs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    For this exercise, you must simulate a CPU with a single core.
    You will receive an arraylist of tasks as input. Each task has a processing
    time which is the time it needs to run in order to fully execute.

    The CPU must choose the task with the shortest processing time and create
    a new thread for it. The main thread should wait for the task to fully
    execute and then join with it, before starting the next task.

    Once a task is fully executed, add its ID to the executed tasks arraylist.
    Use the tests provided in the test folder to ensure your code works correctly.
 */

public class CPU_Simulator
{
    public static class Task implements Runnable {
        long processingTime;
        String ID;
        public Task(String ID, long processingTime) {
        // TODO
            this.ID = ID;
            this.processingTime = processingTime;
        }

    /*
        Simulate running a task by utilizing the sleep method for the duration of
        the task's processingTime. The processing time is given in milliseconds.
    */
        @Override
        public void run() {
        // TODO
            try {
                Thread.sleep(processingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        The startProcessing function should be called at the start of your program.
        Here the CPU selects the next shortest task to run (also known as the
        shortest task first scheduling algorithm) and creates a thread for it to run.
    */
    public ArrayList<String> startSimulation(ArrayList<Task> tasks)  {
        ArrayList<String> executedTasks = new ArrayList<>();

        int loopSize = tasks.size();
        for (int j=0; j<loopSize; j++)
        {
            long minTime = tasks.get(0).processingTime;
            Task smallTask = tasks.get(0);
            int index = 0;
            for (int i=1; i<tasks.size(); i++)
            {
                if (tasks.get(i).processingTime < minTime)
                {
                    minTime = tasks.get(i).processingTime;
                    smallTask = tasks.get(i);
                    index = i;
                }
            }
            tasks.remove(index);
            Thread myThread = new Thread(smallTask);
            myThread.start();
            try {
                myThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executedTasks.add(smallTask.ID);
        }
        // TODO


        return executedTasks;
    }

    public static void main(String[] args) {
    }
}
