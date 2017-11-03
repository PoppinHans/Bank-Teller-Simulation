/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiang
 */
public class BankSim {
    public static boolean multipleLine;
//    is there only one waiting line or severals
    public static ExpressLineTeller expLine;
//    expLine is the "teller" for the ExpressLine.
    public static boolean express;
    public static Teller[] tellers;
    public static TimeLine timeLine;
//    the main time line, which contain all the time that some events might happened.
//    or let's say it's the time that there might be any change to any part of system.
    public static CustomerArrival cus;
    public static Stat[] stat;
//    we need a Statistic list for each teller.
    public static WaitLine[] waitLine;
//    we need several wait line if their is multiple lines.
    public static WaitLine expressLine;
    
    public static void main(String[] args){
        multipleLine = true;
        express = false;
        tellers = new Teller[8];
//        set up the environment of bank.
        
        if(multipleLine){
            waitLine = new WaitLine[tellers.length];
            for (int i = 0; i < tellers.length; i++) {
                waitLine[i] = new WaitLine();
            }
        } else {
            waitLine = new WaitLine[1];
            waitLine[0] = new WaitLine();
        }
        
        int statNum = express ? (tellers.length + 1) : tellers.length;
//        we need a stat for express line if there is. 
        stat = new Stat[statNum];
       
        timeLine = new TimeLine();
        int[] servTime = {45, 70, 100, 100, 190, 190, 190, 190, 240, 250, 420, 500};
        cus = new CustomerArrival(servTime);
        for (int i = 0; i < tellers.length; i++) {
            if(multipleLine)
                tellers[i] = new Teller(i, i);
//            the first is the teller index, and the second is the wait line index.
//            pass the index i, and wait line i if need.
            else
                tellers[i] = new Teller(i, 0);
//            since only one wait line, all tellers use the 1st line. 
        }
        for (int i = 0; i < stat.length; i++) {
            stat[i] = new Stat();
        }
        if(express){
            expressLine = new WaitLine();
            expLine = new ExpressLineTeller(statNum - 1);
        }
        while(timeLine.getCurrentTime() < 43200){
            cus.run();
//            the sequence must be first arrive a customer, and then do the service.
            
            if(express)
                expLine.run();
            
            for (int i = 0; i < tellers.length; i++) 
                tellers[i].run();
            timeLine.goNextTime();
//            since the timeLine contain all the time some events might happened, go next means...
//            ...that go the next point where another event might happened, since all the Events..
//            ... have run now.
            
        }
        int frustrateNum = 0;
        for (int i = 0; i < stat.length; i++) {
            System.out.println("the " + (i+1) + " teller");
            stat[i].displayStats();
            System.out.println("the number of annoying customers go through this teller: " +stat[i].frustrateNum());
            System.out.println("\n\n");
            frustrateNum += stat[i].frustrateNum();
//            the last stat, which said to be teller, is actually expressLine teller if there is one.
        }
        
        System.out.println("the number of annoying customers who get serviceL: " + frustrateNum);
        
        for (int i = 0; i < waitLine.length; i++) {
            frustrateNum += waitLine[i].length();
//            assume that the people wait in the line are annoying...
//            ...so if the number is small, we can ignore...
//            ...however, if the number left in the wait line is large...
//            ...
        }
        System.out.println("the whole number of annoying customers is: "  + frustrateNum);
    }
}
