import java.util.*;
public class Ai2
{
    final int turn;
    final ArrayList<Integer> history;
    Node beginn;
    public Ai2(int pturn,ArrayList<Integer> phistory)
    {
        turn=pturn;
        history=phistory;
    }

    public Viergew recreate(ArrayList<Integer> further)
    {
        ArrayList<Integer> history2=(ArrayList<Integer>)history.clone();
        history2.addAll(further);
        Viergew vier=new Viergew();
        for (int num : history2)
        {
            vier.setOn(num);
        }
        return vier;
    }
    
    public int aiTurn()
    {
        final int sp=turn%2+1;
        ArrayList<Integer> whynot=new ArrayList<Integer>();
        
        int num = (int)(Math.random() * whynot.size());
        return whynot.get(num);
    }
    
    public void beginGeneration()
    {
        beginn=new Node();
        ArrayList<Integer> hist=new ArrayList<Integer>();
        final int sp=turn%2+1;
        Viergew test;
        
        for(int i=0;i<7;i++)
        {
            test=recreate(hist);
            if(test.setOn(i))
            {
                if(test.checkwinner()==-1)
                {
                    ArrayList<Integer>hist2=hist;
                    hist2.add(i);
                   Node nc=new Node(i,0);
                   generateNodes(nc,hist2);
                   beginn.addChild(nc);
                   continue;
                }
                if(test.checkwinner()==0)
                {
                   Node nc=new Node(i,0,true);
                   beginn.addChild(nc);
                   continue;
                }
                if(test.checkwinner()==sp)
                {
                   Node nc=new Node(i,1,true);
                   beginn.addChild(nc);
                   continue;
                }
                Node nc=new Node(i,-1,true);
                beginn.addChild(nc);
            }
        }
    }
    
    public void generateNodes(Node n,ArrayList<Integer> hist)
    {
        final int sp=turn%2+1;
        Viergew test;
        for(int i=0;i<7;i++)
        {
            test=recreate(hist);
            if(test.setOn(i))
            {
                if(test.checkwinner()==-1)
                {
                    ArrayList<Integer>hist2=hist;
                    hist2.add(i);
                   Node nc=new Node();
                   generateNodes(nc,hist2);
                   n.addChild(nc);
                   continue;
                }
                if(test.checkwinner()==0)
                {
                   Node nc=new Node(0);
                   n.addChild(nc);
                   continue;
                }
                if(test.checkwinner()==sp)
                {
                   Node nc=new Node(1);
                   n.addChild(nc);
                   continue;
                }
                Node nc=new Node(-1);
                n.addChild(nc);
            }
        }
    }
    
    /*public Viergew recreate(int further)
    {
        ArrayList<Integer> history2=(ArrayList<Integer>)history.clone();
        history2.add(further);
        Viergew vier=new Viergew();
        for (int num : history2)
        {
            vier.setOn(num);
        }
        return vier;
    }

    public Viergew recreate()
    {
        Viergew vier=new Viergew();
        for (int num : history)
        {
            vier.setOn(num);
        }
        return vier;
    }*/
}
