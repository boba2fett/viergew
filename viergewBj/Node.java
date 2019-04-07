import java.util.*;
public class Node
    {
        int value=0;
        ArrayList<Node> childnodes;
        boolean terminal=false;
        int num=-1;
        public Node(int pvalue)
        {
            childnodes=new ArrayList<Node>();
            value=pvalue;
            terminal=true;
        }
        
        public Node()
        {
            childnodes=new ArrayList<Node>();
        }
        
        public Node(int pnum,int pvalue)
        {
            childnodes=new ArrayList<Node>();
            num=pnum;
            value=pvalue;
        }
        public Node(int pnum,int pvalue,boolean pterminal)
        {
            childnodes=new ArrayList<Node>();
            num=pnum;
            value=pvalue;
            terminal=pterminal;
        }
        
        public int num()
        {
            return num;
        }
        
        public void addChild(Node n)
        {
            childnodes.add(n);
        }

        public boolean isTerminal()
        {
            return terminal;
        }

        public int value()
        {
            return value;
        }
        
        public ArrayList<Node> childnodes()
        {
            return childnodes;
        }
    }