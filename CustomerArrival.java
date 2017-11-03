/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiang
 */
public class CustomerArrival {
    public CustomerArrival(int[] serv) {
        serviceDist = serv;
        currentTime = 0;
    }

    private static int randomArriveTime(){
         int arrtime = 30;
        int i = (int)Math.floor(Math.random() * 10);
    	if(i==0)
    	{
    		arrtime = arrtime + arrtime*3/4;
    	}
    	if(i==1 || i==2)
    	{
    		arrtime = arrtime + arrtime/2;
    	}
    	if(i==3 || i==4)
    	{
    		arrtime = arrtime + arrtime/5;
    	}
    	if(i==5 || i==6)
    	{
    		arrtime = arrtime - arrtime/5;
    	}
    	if(i==7 || i==8)
    	{
    		arrtime = arrtime - arrtime/2;
    	}
    	if(i==9)
    	{
    		arrtime = arrtime - arrtime*3/4;
    	}
//        this will return 10% for 1st case, and 20% for 2nd case, 20% for 3rd case, which fit in...
//        ... the condition of project3.
        return arrtime;
    }
    // methods
    private static void updateTime(){
        currentTime += randomArriveTime();
//        using for compute the next time this Event shall run.
    }
    
    private static int intRandomInterval(int low, int high) {
        return (int) Math.floor((high - low) * Math.random() + low + 0.5);
    }
    private static int selectServiceTime() {
        return serviceDist[intRandomInterval(0, serviceDist.length - 1)];
    }
    public static void run() {
        if(currentTime > BankSim.timeLine.getCurrentTime())
            return;
//        which means if it's not the time to run, just stop the method.
        Customer c = new Customer(BankSim.timeLine.getCurrentTime(), 
                selectServiceTime());
//        this part is just the same as CarMaker, which computer the random service time.
        updateTime();
//        put off the time of this to the time that next time it's legal for customer arrival to run.
        BankSim.timeLine.addEventTime(currentTime);
//        let the timeLine get the time, and called back if time is up.
    }
    private int interval;
    private static int[] serviceDist;
    private static int currentTime;
//    this present the very next time the customer arrival might run.
}
