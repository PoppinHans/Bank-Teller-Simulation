/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiang
 */
public class Stat {
    public Stat(){
        lastUpdateTime = 0;
        oldQLength = 0;
        lastQUpdateTime = 0;
    
        count = 0;
        totalTime = 0;
        busyTime = 0;
        idleTime = 0;
        maxWait = 0;
        averageWait = 0;
        maxQLength = 0;
        averageQLength = 0; 
        averageServiceTime = 0;
    }
    public void updateQueueStats(int time, int qlen) {
        
        if (qlen > maxQLength)
          maxQLength = qlen;
        averageQLength += oldQLength * (time - lastQUpdateTime);
        totalTime += time - lastQUpdateTime;
        lastQUpdateTime = time;
        oldQLength = qlen;
    }
    public void updateBusyTimeStats(int time) {
        busyTime += time - lastUpdateTime;
        lastUpdateTime = time;
    }        
    public void updateIdleTimeStats(int time) {
        
        idleTime += time - lastUpdateTime;
        lastUpdateTime = time;
    }
    public void updateServiceTimeStats(int st) {
   
        averageServiceTime += st;
    }
    public void updateWaitTimeStats(int time, int enterTime) {
    
        int wait = time - enterTime;
        if (wait > 438)
            updateCrazyTimes();
        if (wait > maxWait){
          maxWait = wait;
        }
        averageWait += wait;
        count++;  // another leaves the waiting queue
    } 
    public void updateCrazyTimes(){
        frustrateTimes++;
    }
    
    public int frustrateNum(){
        return frustrateTimes;
    }
    
    public void displayStats() {
        System.out.println("\n** Simulation Results **\n");
        System.out.println(" Calculated Simulation Time: " + totalTime);
        System.out.println(" Idle Time: " + idleTime);
        System.out.println(" Busy Time: " + busyTime);
        System.out.println(" (Busy Time based on service time: " + 
                                               averageServiceTime + ")\n");
        System.out.println(" Maximum Queue Length: " + maxQLength);
        System.out.println(" Avg. Queue Length: " + averageQLength/totalTime);
        System.out.println(" Maximum Waiting Time: " + maxWait);
        System.out.println(" Avg. Waiting Time for customers through the queue: " +
                                                 averageWait/count);
        System.out.println(" Avg. Service Time: " + averageServiceTime/
                                                       count);
        System.out.println(" Number of customers through system: " + count);
    }  // displayStats
//     private variables
    public void printBusy(){
        System.out.println(busyTime);
    }
    private int lastUpdateTime;
    private int oldQLength;
    private int lastQUpdateTime;
    
    private double count;
    private int totalTime;
    private int busyTime;
    private int idleTime;
    private int maxWait;
    private double averageWait;
    private int maxQLength;
    private double averageQLength; 
    private double averageServiceTime;
    private int frustrateTimes;
}
