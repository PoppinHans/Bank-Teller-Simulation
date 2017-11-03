/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiang
 */
public class Teller implements Event{
    public Teller(int i, int w){
        servTime = 0;
        currentTime = 0;
        index = i;
        waitLineIndex = w;
        busy = false;
    }
    
    public boolean isBusy(){
        return busy;
    }
    
    public void run(){
        
        BankSim.stat[index].updateQueueStats(BankSim.timeLine.getCurrentTime(), 
                BankSim.waitLine[waitLineIndex].length());
//         since I update the "current time" from the timeline, and this is not part of running at all, just about...
//        ... queue's length. so I can update every Events' time. 
        if(busy)
            BankSim.stat[index].updateBusyTimeStats(BankSim.timeLine.getCurrentTime());
//        update the busy time every time too. 
        if(currentTime > BankSim.timeLine.getCurrentTime())
            return;
//        if it's still time to do something, just stop method.
        Customer customer;
        if(BankSim.waitLine[waitLineIndex].length() == 0){
            if(!BankSim.express){
                busy = false;
                BankSim.stat[index].updateIdleTimeStats(BankSim.timeLine.getCurrentTime());
                return;
//                let's suppose the teller will take person from express line only if there is.
//                no person in this line, and some person in the express line, which nearly never happened.
            } else {
                if(BankSim.expressLine.length() == 0){
                    busy = false;
                    BankSim.stat[index].updateIdleTimeStats(BankSim.timeLine.getCurrentTime());
                    return;
                }
                busy = true;
//                this is the time that it does take some one from the expressLine.
                customer = BankSim.expressLine.remove();
            }
        }else {
            busy = true;
//            this is the most common case, which means that it take some one from the wait line.
            customer = BankSim.waitLine[waitLineIndex].remove();
        }
        if(currentTime < BankSim.timeLine.getCurrentTime()){
            servTime = 0;
//            since the teller is doing nothing for some time, since the currentTime, which means...
//            ... the time that 
            BankSim.stat[index].updateIdleTimeStats(BankSim.timeLine.getCurrentTime());
        }
        else {
            BankSim.stat[index].updateBusyTimeStats(BankSim.timeLine.getCurrentTime());
        }
        BankSim.stat[index].updateServiceTimeStats(customer.getServiceTime());
        BankSim.stat[index].updateWaitTimeStats(BankSim.timeLine.getCurrentTime(), 
                customer.getArrivalTime());
        servTime += customer.getServiceTime();
        currentTime = BankSim.timeLine.getCurrentTime() + customer.getServiceTime();
        BankSim.timeLine.addEventTime(currentTime);
        
    }
    private int waitLineIndex;
    private boolean busy;
    private int index;
    private int servTime;
    private int currentTime;
    private int length;
}
