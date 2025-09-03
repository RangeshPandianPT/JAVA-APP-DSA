public class Traversing
{
    static class Node 
	{
        int DATA;     
        Node NEXT;    

        Node(int value) 
		{
            this.DATA = value;
            this.NEXT = null;
        }
    }

    public static void traverse(Node START) 
	{
        Node PTR = START;   

        while (PTR != null) 
		{   
            System.out.print(PTR.DATA + " ");  
            PTR = PTR.NEXT;    
        }
        System.out.println();  
    }

    public static void main(String[] args) {
        Node START = new Node(10);
        START.NEXT = new Node(20);
        START.NEXT.NEXT = new Node(30);
        START.NEXT.NEXT.NEXT = new Node(40);

        System.out.println("Traversed Linked List:");
        traverse(START);
    }
}
