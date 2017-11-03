/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiang
 */
public class Customer {
    public Customer(int t, int servTime) {
        arrivalTime = t;
        serviceTime = servTime;
        if(BankSim.express && serviceTime == 45){
            BankSim.expressLine.enter(this);
        }
        else{
            if(BankSim.multipleLine){
                int bestLine = 0;
//                actually the assume index for best line to put a new customer.
                int length = BankSim.waitLine[0].length() + (BankSim.tellers[0].isBusy() ? 1 : 0);
//                since we want the new customer go to the empty teller, but not the one with...
//                ... empty wait line, but actually a customer doing the service. it's equal to 1 if...
//                ... it's busy.
                for (int i = 0; i < BankSim.waitLine.length; i++) {
                    int busy = BankSim.tellers[i].isBusy() ? 1 : 0;
                    if(length > BankSim.waitLine[i].length() + busy){
                        bestLine = i;
                        length = BankSim.waitLine[i].length();
                    } 
//                    search the best line to put in, from the length of line, since the bank cannot ...
//                    ... asking about the customer how much time they are left, we don't know exactly ...
//                    ... time for waiting. only way I come out is to use the length.
                }
                BankSim.waitLine[bestLine].enter(this);
            }
            else
                BankSim.waitLine[0].enter(this);
        }
//        to do
        
//          CarSim.washer[CarSim.wasIndex].run();  // wake-up washer if idle
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
  
    public int getServiceTime() {
        return serviceTime;
    }
    // private variables
         
    private int arrivalTime;
    private int serviceTime;
}
