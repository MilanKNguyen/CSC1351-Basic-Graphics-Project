/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicgraphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;

/**
 *
 * @author sbrandt
 */
public class Clock {

    private static Timer t = null;
    private static List<Task> newTasks = new ArrayList<>();
    private static List<Task> runningTasks = new ArrayList<>();

    public static void addTask(Task task) {
        if (task.isSubmitted()) {
            return;
        }
        task.setSubmitted();
        synchronized (newTasks) {
            boolean empty = newTasks.isEmpty();
            newTasks.add(task);
            if (empty) {
                newTasks.notifyAll();
            }
        }
    }

    public static void stop() {
        t.cancel();
    }

    public static void start(int period) {
        if (t != null) {
//            throw new GuiException("SpriteComponent already started");
            t.cancel();
        }
        t = new Timer();
        TimerTask tt = new TimerTask() {
            int toc = 0;

            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (newTasks) {
                            while (newTasks.isEmpty() && runningTasks.isEmpty()) {
                                try {
                                    newTasks.wait();
                                } catch (InterruptedException ex) {
                                }
                            }
                            runningTasks.addAll(newTasks);
                            newTasks.clear();
                            Iterator<Task> iter = runningTasks.iterator();
                            while (iter.hasNext()) {
                                Task t = iter.next();
                                t.run_();
                                if (t.isFinished()) {
                                    iter.remove();
                                }
                            }
                        }
                    }
                });
            }
        };
        t.schedule(tt, 0, period);
    }
}
