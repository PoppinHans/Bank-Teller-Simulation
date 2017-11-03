/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiang
 */
public class ListList <T extends Comparable<T>>{
    public void print(){
        back = first;
        System.out.println(first.getData());
        while(back.getNext() != null){
            back = back.getNext();
            System.out.println(back.getData());
        }
    }
    
    public ListList(boolean d, boolean s, boolean i){
        duplicate = d;
        sorted = s;
        increasing = i;
    }
    
    public ListList(ListNode<T> a,boolean d, boolean s, boolean i){
        first = a;
        duplicate = d;
        sorted = s;
        increasing = i;
    }
    
    public ListList(T obj,boolean d, boolean s, boolean i){
        first = new ListNode();
        first.setData(obj);
        duplicate = d;
        sorted = s;
        increasing = i;
    }
    
    public ListList(T obj, ListNode<T> a,boolean d, boolean s, boolean i){
        first = new ListNode();
        first.setData(obj);
        first.setNext(a);
        duplicate = d;
        sorted = s;
        increasing = i;
    }
    
    public int length(){
        int count = 1;
        if (first == null)
            return 0;
        back = first;
        while(back.getNext() != null){
            back = back.getNext();
            count ++;
        }
        return count;
    }
    public T first(){
        if(first != null)
            return first.getData();
        return null;
    }
    public T last(){
        back = first;
        if(first == null)
            return null;
        while(back.getNext() != null)
            back = back.getNext();
        return back.getData();
    }
    
    public boolean lookup(T obj){
        back = first;
        if (first == null)
            return false;
        if (back.getData().equals(obj))
            return true;
        while(back.getNext() != null){
            back = back.getNext();
            if (back.getData().equals(obj))
                return true;
        }
        return false;
    }
// searches the List for an Object equal to obj and returns
// true if found, false otherwise.

    public T find(int n){
        int count = n;
        if (n <= 0)
            return null;
        back = first;
        while(count > 1 && back.getNext() != null){
            back = back.getNext();
            count--;
        }
        if (count == 1)
            return back.getData();
        return null;
    }
// counting from the start of the List, returns the *data* from the
// nth node in the List; if the List is not at least n long, 
// null is returned; find(1) returns the first data; 
// find(0) and find(-n) return null

    public int find(T obj){
        back = first;
        int count = 1;
        if (back.getData().equals(obj))
            return count;
        while(back.getNext() != null){
            back = back.getNext();
            count ++ ;
            if(back.getData().equals(obj))
                return count;
        }
        return 0;
    }
// returns the position here the obj is found in the List.
// 0 is returned if obj is not present in the List. 
// The two find() methods are inverses of each other, that is
// db.find(db.find(5)) --> 5

    
    public void delete(T obj){
        back = first;
        if(back.getNext() == null){
            if(back.getData().equals(obj))
                first = null;
        } else {
            if(first.getData().equals(obj))
                first = first.getNext();
            while(back.getNext() != null){
                if(back.getNext().getData().equals(obj)){
                    back.setNext(back.getNext().getNext());
                    return;
                }
                back = back.getNext();
            }
            
        }
    }
// removes the node containing the data in obj from the List;
// if no such node exists, nothing happens

  
    public void delete(int n){
        if (n == 1){
            if (first.getNext() != null){
                if(current == first)
                    current = null;
                first = first.getNext();
            }
            else{
                first = null;
                current = null;
            }
        }
        int count = n;
        back = first;
        while(count > 2 && back.getNext() != null){
            back = back.getNext();
            count --;
        }
        if (count == 2){
            if(current == back.getNext())
                current = null;
            if(back.getNext().getNext() != null)
                back.setNext(back.getNext().getNext());
            else
                back.setNext(null);
        }
    }
// removes the nth node from the List; if there are not at least
// n nodes, nothing happens; delete(0) and delete(-n) do nothing

    public void add(T obj){
        ListNode<T> a = new ListNode(obj);
        if(first == null)
            first = new ListNode(obj);
        if(!sorted){
        if(!duplicate){
//no sort no duplicate
            T something = last();
            back.setNext(a);
        } else {
//only duplicate
            back = first;
                if (back.getData().compareTo(obj) == 0){
                    return;
                }
            
            while(back.getNext() != null){
                back = back.getNext();
                if (back.getData().compareTo(obj) == 0){
                    System.out.println("");
                    return;
                }
            }
            
            back.setNext(a);
        }
        } else {
//only sort
            if (increasing){
                back = first;
                if(obj.compareTo(back.getData()) < 0){
                        back = new ListNode(obj);
                        back.setNext(first);
                        first = back;
                        return;
                }
                if(!duplicate){
                    if(back.getNext() == null){
                        back = new ListNode(obj);
                        first.setNext(back);
                        return;
                    }
                    while(obj.compareTo(back.getNext().getData()) > 0){
                        back = back.getNext();
                        if(back.getNext() == null)
                            break;
                    }
                    ListNode<T> something = new ListNode(obj);
                    if(back.getNext() != null)
                        something.setNext(back.getNext());
                    back.setNext(something);
                }else{
//increasing duplicate
                    if(back.getNext() == null){
                        if (back.getData().compareTo(obj) != 0){
                            back = new ListNode(obj);
                            first.setNext(back);
                        }
                        return;
                    }
                    if (back.getData().equals(obj)){
                        return;
                    }
                    while(obj.compareTo(back.getNext().getData()) >= 0){
                        back = back.getNext();
                        if(obj.compareTo(back.getData()) == 0){
                            return;
                        }
                        if(back.getNext() == null){
                            break;
                        }
                    }
                    ListNode<T> something = new ListNode(obj);
                    something.setNext(back.getNext());
                    back.setNext(something);
                    
                }
            } else {
//decreasing 
                back = first;
                if(obj.compareTo(back.getData()) > 0){
                        back = new ListNode(obj);
                        back.setNext(first);
                        first = back;
                        return;
                }
                if(!duplicate){
//decreasing with duplicate
                    if(back.getNext() == null){
                        back = new ListNode(obj);
                        first.setNext(back);
                        return;
                    }
                    while(obj.compareTo(back.getNext().getData()) < 0){
                        back = back.getNext();
                        if(back.getNext() == null)
                            break;
                    }
                    ListNode<T> something = new ListNode(obj);
                    if(back.getNext() != null)
                        something.setNext(back.getNext());
                    back.setNext(something);
                }else{
//decreasing duplicate true
                    if (back.getData().equals(obj))
                        return;
                    if(back.getNext() == null){
                        back = new ListNode(obj);
                        first.setNext(back);
                        return;
                    }
                    while(obj.compareTo(back.getNext().getData()) <= 0){
                        back = back.getNext();
                        if(back.getData().compareTo(obj) == 0){
                            return;
                        }
                        if(back.getNext() == null){
                            break;
                        }
                    }
                    ListNode<T> something = new ListNode(obj);
                    if(back.getNext() != null)
                        something.setNext(back.getNext());
                    back.setNext(something);
                    
                }
                
            }
        }
    }
    
    public void reset(){
        current = null;
    }
        
    
// inserts a new node with data obj into the List according to the
// parameters set at the construction of this List instance.

    public T getNext(){
        if(first == null)
            return null;
        if(current == null){
            current = first;
            return first.getData();
        }
        if(current.getNext() != null){
            current = current.getNext();
            return current.getData();
        }
        return null;
    }
// using an internally maintained pointer, getNext()
// returns the *data* Object at the start of the List the first
// time it is called, the next item the second time it is called,
// and so on; if the List is empty or the last item in the List
// has already been returned, null is returned.

   public T getPrevious(){
       back = first;
       if(current == null){
           return null;
       }
       while(back.getNext() != current && back.getNext() != null){
           back = back.getNext();
       }
       if (back.getNext() == current){
           current = back;
           return current.getData();
       }
       return null;
   }
// using an the same internally maintained pointer as getNext(),
// getPrevious() returns the *data* object in the List immediately 
// preceding the *data* object that was returned previously by
// either getNext() or getPrevious(); if there is no previous 
// *data* object returned or the first *data* object was most 
// recently returned, null is returned.


   
   
   private ListNode<T> first = null;
   private ListNode<T> back = first;
   private ListNode<T> current = null;
   private boolean duplicate;
   private boolean sorted;
   private boolean increasing;
}
