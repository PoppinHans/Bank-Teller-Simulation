/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiang
 */
public class ListNode<T extends Comparable<T>>{
    public ListNode() {}
    
    public ListNode(ListNode<T> link){
        data = null;
        next = link;
    }
    
    public ListNode(T something){
        data = something;
        next = null;
    }
    
    public ListNode(T something, ListNode<T> link){
        data = something;
        next = link;
    }
    
    public T getData(){
        return data;
    }
    
    public void setData(T something){
        data = something;
    }
    
    public ListNode <T> getNext(){
        return next;
    }
    
    public void setNext(ListNode<T> link){
        next = link;
    } 
    
    private T data;
    private ListNode <T> next;
}
