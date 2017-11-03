
import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiang
 */
public class WaitLine {
    public void enter(Customer c){
        customers.add(c);
    }
    
    public Customer remove(){
        return customers.poll();
    }
    
    public int length(){
        return customers.size();
    }
    private Queue<Customer> customers = new LinkedList();
}
