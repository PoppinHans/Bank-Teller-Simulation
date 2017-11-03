/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiang
 */
public class ExpressLineTeller {
    public ExpressLineTeller(int i){
        currentTime = 0;
        servTime = 0;
        index = i;
        busy = false;
    }
    public void run(){
        
        BankSim.stat[index].updateQueueStats(BankSim.timeLine.getCurrentTime(), 
                BankSim.expressLine.length());
        if(busy){
            BankSim.stat[index].updateBusyTimeStats(BankSim.timeLine.getCurrentTime());
        }
       
        if(currentTime > BankSim.timeLine.getCurrentTime())
            return;

        if(BankSim.expressLine.length() == 0){
            busy = false;
            return;
        }
        Customer customer = BankSim.expressLine.remove();
        busy = true;

        if(currentTime < BankSim.timeLine.getCurrentTime()){
            servTime = 0;
            BankSim.stat[index].updateIdleTimeStats(BankSim.timeLine.getCurrentTime());
        }
        BankSim.stat[index].updateServiceTimeStats(customer.getServiceTime());
        BankSim.stat[index].updateWaitTimeStats(BankSim.timeLine.getCurrentTime(), 
                customer.getArrivalTime());
        servTime += customer.getServiceTime();
        currentTime  = BankSim.timeLine.getCurrentTime() + customer.getServiceTime();
        BankSim.timeLine.addEventTime(currentTime);
        
    }
    private int index;
    private int servTime;
    private int currentTime;
    private boolean busy;
}
