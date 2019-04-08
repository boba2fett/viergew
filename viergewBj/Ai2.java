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
        beginGeneration();
        ArrayList<Integer> whynot=new ArrayList<Integer>();
        ArrayList<Node> cns=beginn.childnodes();
        for(Node n:cns)
        {
            if(minimax(n,50,true)==1)
            {
                whynot.add(n.num());
            }
        }
        int num = (int)(Math.random() * whynot.size());
        if(whynot.size()!=0)
        {
            return whynot.get(num);
        }

        for(Node n:cns)
        {
            if(minimax(n,50,true)==0)
            {
                whynot.add(n.num());
            }
        }
        num = (int)(Math.random() * whynot.size());
        if(whynot.size()!=0)
        {
            return whynot.get(num);
        }
        return -1;
    }

    public void beginGeneration()
    {
        beginn=new Node();
        final int sp=turn%2+1;
        final int gsp=(1+turn)%2+1;
        Viergew test;

        for(int i=0;i<7;i++)
        {
            test=recreate(new ArrayList<Integer>());
            if(test.setOn(i))
            {
                if(test.checkwinner()==-1)
                {
                    ArrayList<Integer>hist2=history;
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
                if(test.checkwinner()==gsp)
                {
                    Node nc=new Node(i,-1,true);
                    beginn.addChild(nc);
                    continue;
                }
            }
        }
    }

    public void generateNodes(Node n,ArrayList<Integer> hist)
    {
        final int sp=turn%2+1;
        final int gsp=(1+turn)%2+1;
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
                if(test.checkwinner()==gsp)
                {
                    Node nc=new Node(-1);
                    n.addChild(nc);
                    continue;
                }
            }
        }
    }

    public int minimax(Node node,int depth,boolean maximizingPlayer)
    {
        int value;
        if(depth == 0 || node.isTerminal())
        {
            return node.value();
        }
        if(maximizingPlayer)
        {
            value = -1;
            for(Node child:node.childnodes())
            {
                value = Math.max(value, minimax(child,depth-1, false));
            }
        }
        else
        {
            value = 1;
            for(Node child:node.childnodes())
            {
                value = Math.min(value, minimax(child,depth-1, true));
            }
        }
        return value;
    }
}
