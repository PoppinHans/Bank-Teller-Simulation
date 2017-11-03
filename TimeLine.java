/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiang
 */
public class TimeLine {
    public TimeLine(){
        time = new ListList(true, true, true);
//        which means just the ListList we did in the project 2, that is increasing and unduplicate.
        time.add(0);
    }
    
    public void addEventTime(int t){
        time.add(t);
    }
    
    public int getCurrentTime(){
        return time.first();
    }
    
    public void goNextTime(){
        time.delete(1);
//        since the 1st time is the current time.
    }
            
    private static ListList<Integer> time;
    
}
