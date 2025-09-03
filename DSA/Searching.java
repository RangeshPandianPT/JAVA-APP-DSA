import java.util.Scanner;

class Searching
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

    
    public static Node search(Node START, int VAL) 
	{
        Node PTR = START;   

        while (PTR != null) 
		{   
            if (VAL == PTR.DATA) 
			{   
                Node POS = PTR;      
                return POS;          
            } else 
			{
                PTR = PTR.NEXT;      
            }
        }

        Node POS = null;
        return POS;   
    }

    public static void main(String[] args) 
	{
        Scanner sc = new Scanner(System.in);

        Node START = new Node(10);
        START.NEXT = new Node(20);
        START.NEXT.NEXT = new Node(30);
        START.NEXT.NEXT.NEXT = new Node(40);

        System.out.print("Enter value to search: ");
        int VAL = sc.nextInt();

        Node result = search(START, VAL);

        if (result != null)
            System.out.println("Value " + VAL + " found in node with DATA = " + result.DATA);
        else
            System.out.println("Value " + VAL + " not found in the linked list.");

        sc.close();
    }
}
