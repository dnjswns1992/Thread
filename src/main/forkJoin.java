package src.main;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class forkJoin extends RecursiveAction {

    private long workLoad = 0;

    public forkJoin(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {
        String threadName = Thread.currentThread().getName();

        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16) {
            System.out.println("[" + LocalTime.now() + "][" + threadName + "]"
                    + " Splitting workLoad : " + this.workLoad);
            sleep(1000);
            List<forkJoin> subtasks =
                    new ArrayList<>();

            subtasks.addAll(createSubtasks());

            for(RecursiveAction subtask : subtasks){
                subtask.fork();
            }

        } else {
            System.out.println("[" + LocalTime.now() + "][" + threadName + "]"
                    + " Doing workLoad myself: " + this.workLoad);
        }
    }

    private List<forkJoin> createSubtasks() {
        List<forkJoin> subtasks =
                new ArrayList<>();

        forkJoin subtask1 = new forkJoin(this.workLoad / 2);
        forkJoin subtask2 = new forkJoin(this.workLoad / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
