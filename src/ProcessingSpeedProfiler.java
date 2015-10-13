import java.util.TimerTask;

/**
 * Created by Thijs on 12-10-2015.
 */
public class ProcessingSpeedProfiler extends TimerTask {

    public static int processedCount;
    public static int processedCountDirect;
    private static int step;

    public void run(){
        step++;
//        System.out.println("Step: " + step + " | Processing Count: " + processedCount + "| Direct: " + processedCountDirect );
        processedCount = 0;
        processedCountDirect = 0;
    }

    public synchronized static void addProcessingCount(){
        processedCount++;
    }
}
