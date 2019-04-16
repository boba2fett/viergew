import java.util.*;
public class AiNodes
{
    final int turn;
    final ArrayList<Integer> history;
    final int w;
    Node beginn;
    public AiNodes(int turn,ArrayList<Integer> history,int width)
    {
        this.turn=turn;
        this.history=history;
        w=width;
    }

    class outstandingMoveException extends Exception
    {
        // Konstruktor unserer eigenen Exception
        outstandingMoveException()
        {
            // Aufruf des übergeordneten Konstruktors mit dem zu
            // erscheinenden Fehlertext
            super("Feld nicht verfügbar");
        }
    }

    public Viergew recreate2(ArrayList<Integer> further) throws outstandingMoveException
    {
        ArrayList<Integer> history2=(ArrayList<Integer>)history.clone();
        history2.addAll(further);
        Viergew vier=new Viergew();
        for (int num : history2)
        {
            if(!vier.setOn(num))
            {
                throw new outstandingMoveException();
            }
        }
        return vier;
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

    public int aiTurn2()
    {
        if(turn==0)
        {
            return 3;
        }
        beginGeneration(9);
        ArrayList<Integer> whynot=new ArrayList<Integer>();
        System.out.println(minimax(beginn,true));
        ArrayList<Node> cns=beginn.childnodes();
        int num;
        for(int i=2;i>=-2;i--)
        {
            for(Node n:cns)
            {
                if(minimax(n,false)==i)
                {
                    whynot.add(n.num());
                }
            }
            num = (int)(Math.random() * whynot.size());
            if(whynot.size()!=0)
            {
                return whynot.get(num);
            }
        }
        whynot=possible();
        num = (int)(Math.random() * whynot.size());
        return whynot.get(num);

    }

    public void beginGeneration()
    {
        beginn=new Node();
        final int sp=turn%2+1;
        final int gsp=(1+turn)%2+1;
        Viergew test;

        for(int i=0;i<w;i++)
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
        for(int i=0;i<w;i++)
        {
            test=recreate(hist);
            if(test.setOn(i))
            {
                Node nc=null;
                if(test.checkwinner()==-1)
                {
                    ArrayList<Integer>hist2=(ArrayList<Integer>)hist.clone();
                    hist2.add(i);
                    nc=new Node();
                    generateNodes(nc,hist2);
                }
                if(test.checkwinner()==0)
                {
                    nc=new Node(0);
                }
                if(test.checkwinner()==sp)
                {
                    nc=new Node(1);
                }
                if(test.checkwinner()==gsp)
                {
                    nc=new Node(-1);
                }
                n.addChild(nc);
            }
        }
    }

    public void beginGeneration(int depth)
    {
        beginn=new Node();
        final int sp=turn%2+1;
        final int gsp=(1+turn)%2+1;
        Viergew test;

        for(int i=0;i<w;i++)
        {
            test=recreate(new ArrayList<Integer>());
            if(test.setOn(i))
            {
                if(test.checkwinner()==-1)
                {
                    ArrayList<Integer>hist2=history;
                    hist2.add(i);
                    Node nc=new Node(i,0);
                    generateNodes(nc,hist2,depth);
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

    public void generateNodes(Node n,ArrayList<Integer> hist,int depth)
    {
        if(depth==0)
        {
            n.setTerminal();
            return;
        }
        final int sp=turn%2+1;
        final int gsp=(1+turn)%2+1;
        Viergew test;
        for(int i=0;i<w;i++)
        {
            test=recreate(hist);
            if(test.setOn(i))
            {
                Node nc=null;
                if(test.checkwinner()==-1)
                {
                    ArrayList<Integer>hist2=(ArrayList<Integer>)hist.clone();
                    hist2.add(i);
                    nc=new Node();
                    generateNodes(nc,hist2,depth-1);
                }
                if(test.checkwinner()==0)
                {
                    nc=new Node(0);
                }
                if(test.checkwinner()==sp)
                {
                    nc=new Node(1);
                }
                if(test.checkwinner()==gsp)
                {
                    nc=new Node(-1);
                }
                n.addChild(nc);
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
            value = -2;
            for(Node child:node.childnodes())
            {
                value = Math.max(value, minimax(child,depth-1, false));
            }
        }
        else
        {
            value = 2;
            for(Node child:node.childnodes())
            {
                value = Math.min(value, minimax(child,depth-1, true));
            }
        }
        return value;
    }

    public int minimax(Node node,boolean maximizingPlayer)
    {
        int value;
        if(node.isTerminal())
        {
            return node.value();
        }
        if(maximizingPlayer)
        {
            value = -2;
            for(Node child:node.childnodes())
            {
                value = Math.max(value, minimax(child, false));
            }
        }
        else
        {
            value = 2;
            for(Node child:node.childnodes())
            {
                value = Math.min(value, minimax(child, true));
            }
        }
        return value;
    }

    private ArrayList<Integer> possible()
    {
        ArrayList<Integer> poss=new ArrayList<Integer>();
        Viergew test;
        for(int i=0;i<w;i++)
        {
            test=recreate(new ArrayList<Integer>());
            if(test.setOn(i))
            {
                poss.add(i);
            }
        }
        return poss;
    }

    public int minimax2(boolean maximizingPlayer,ArrayList<Integer> hist,int depth)
    {
        int value;
        final int sp=turn%2+1;
        final int gsp=(1+turn)%2+1;
        Viergew test;
        if(depth==0)
        {
            return 0;
        }
        if(maximizingPlayer)
        {
            value = -2;

            for(int i=0;i<w;i++)
            {
                test=recreate(hist);

                if(test.setOn(i))
                {
                    if(test.checkwinner()==-1)
                    {
                        ArrayList<Integer>hist2=(ArrayList<Integer>)hist.clone();
                        hist2.add(i);
                        value = Math.max(value, minimax2( false,hist2,depth-1));
                    }
                    if(test.checkwinner()==0)
                    {
                        value = Math.max(value, 0);
                    }
                    if(test.checkwinner()==sp)
                    {
                        value = Math.max(value, 1);
                    }
                    if(test.checkwinner()==gsp)
                    {
                        value = Math.max(value, -1);
                    }
                }
            }
        }
        else
        {
            value = 2;
            for(int i=0;i<w;i++)
            {
                test=recreate(hist);
                if(test.setOn(i))
                {
                    if(test.checkwinner()==-1)
                    {
                        ArrayList<Integer>hist2=(ArrayList<Integer>)hist.clone();
                        hist2.add(i);
                        value = Math.min(value, minimax2( true,hist2,depth-1));
                    }
                    if(test.checkwinner()==0)
                    {
                        value = Math.min(value, 0);
                    }
                    if(test.checkwinner()==sp)
                    {
                        value = Math.min(value, 1);
                    }
                    if(test.checkwinner()==gsp)
                    {
                        value = Math.min(value, -1);
                    }
                }
            }
        }
        return value;
    }

    public int aiTurn()
    {
        if(turn==0)
        {
            return 3;
        }
        ArrayList<Integer> whynot=new ArrayList<Integer>();
        int num;
        for(int i=2;i>=-2;i--)
        {
            for(int j=0;j<w;j++)
            {
                ArrayList<Integer> hist=new ArrayList<Integer>();
                hist.add(j);
                try{
                recreate2(hist);
                if(minimax2(false,hist,6)==i)
                {
                    whynot.add(j);
                }
            }
            catch(outstandingMoveException e)
            {}
            }
            num = (int)(Math.random() * whynot.size());
            if(whynot.size()!=0)
            {
                return whynot.get(num);
            }
        }
        whynot=possible();
        num = (int)(Math.random() * whynot.size());
        return whynot.get(num);

    }
}
