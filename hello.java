import java.util.*;

class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

class GfG{
    
    static void printList(Node n){
        while(n!=null){
            System.out.print(n.data+" ");
            n = n.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        while (T-- > 0) {
            
            int n = sc.nextInt();
            int val = sc.nextInt();
            
            Node first = new Node(val);
            Node tail = first;
            for(int i=0; i<n-1; i++)
            {
                val = sc.nextInt();
                tail.next = new Node(val);
                tail = tail.next;
            }
            
            int m = sc.nextInt();
            val = sc.nextInt();
            
            Node second = new Node(val);
            tail = second;
            for(int i=0; i<m-1; i++)
            {
                val = sc.nextInt();
                tail.next = new Node(val);
                tail = tail.next;
            }
            
            Solution g = new Solution();
            Node res = g.subLinkedList(first, second);
            printList(res);
        }
    }
}

class Solution
{
    static Node reverse(Node head){
        Node prev=null;
        while(head!=null){
            Node temp=head.next;
            head.next=prev;
            prev=head;
            head=temp;
        }
        return prev;
    }
    static int size(Node head){
        int count=0;
        Node temp=head;
        while(temp!=null){
            count++;
            temp=temp.next;
        }
        return count;
    }
    static Node subLinkedList(Node l1, Node l2)
    {
        // code here
        while(l1!=null && l1.data==0){
            l1=l1.next;
        }
        while(l2!=null && l2.data==0){
            l2=l2.next;
        }
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        int countL1=size(l1);
        int countL2=size(l2);
        
        Node max=null;
        Node min=null;
        if(countL1>countL2){
            max=l1;
            min=l2;
        }else if(countL1<countL2){
            max=l2;
            min=l1;
        }else{
            Node temp1=l1;
            Node temp2=l2;
            while(temp1!=null){
                if(temp1.data>temp2.data){
                    max=l1;
                    min=l2;
                    break;
                }else if(temp1.data<temp2.data){
                    max=l2;
                    min=l1;
                    break;
                }
                temp1=temp1.next;
                temp2=temp2.next;
            }
            if(max==null && min==null){
                return new Node(0);
            }
        }
        max=reverse(max);
        min=reverse(min);
        
        Node ans=new Node(-1);
        Node dummy=ans;
        int carry=0;
        
        while(max!=null || min!=null){
            int valueMax=0;
            int valueMin=0;
            if(max!=null){
                valueMax=max.data;
                max=max.next;
            }
            if(min!=null){
                valueMin=min.data;
                min=min.next;
            }
            if(valueMax>=(valueMin+carry)){
                int value=valueMax-(valueMin+carry);
                carry=0;
                dummy.next=new Node(value);
            }else{
                int value=(10+valueMax)-(valueMin+carry);
                carry=1;
                dummy.next=new Node(value);
            }
            dummy=dummy.next;
        }
        
        ans=ans.next;
        ans=reverse(ans);
        while(ans.data==0){
            ans=ans.next;
        }
        return ans;
    }
}