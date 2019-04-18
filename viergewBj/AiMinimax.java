import java.util.*;
public class AiMinimax
{
    final int turn;
    final ArrayList<Integer> history;
    final int w;
    public AiMinimax(int turn,ArrayList<Integer> history,int width)
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
    
    public Viergew recreate(int further)
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

    public int minimax(boolean maximizingPlayer,ArrayList<Integer> hist,int depth)
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
            value = -50;

            for(int i=0;i<w;i++)
            {
                test=recreate(hist);

                if(test.setOn(i))
                {
                    if(test.checkwinner()==-1)
                    {
                        ArrayList<Integer>hist2=(ArrayList<Integer>)hist.clone();
                        hist2.add(i);
                        value = Math.max(value, minimax( false,hist2,depth-1));
                    }
                    if(test.checkwinner()==0)
                    {
                        value = Math.max(value, 0);
                    }
                    if(test.checkwinner()==sp)
                    {
                        value = Math.max(value, depth);
                    }
                    if(test.checkwinner()==gsp)
                    {
                        value = Math.max(value, -depth);
                    }
                }
            }
        }
        else
        {
            value = 50;
            for(int i=0;i<w;i++)
            {
                test=recreate(hist);
                if(test.setOn(i))
                {
                    if(test.checkwinner()==-1)
                    {
                        ArrayList<Integer>hist2=(ArrayList<Integer>)hist.clone();
                        hist2.add(i);
                        value = Math.min(value, minimax( true,hist2,depth-1));
                    }
                    if(test.checkwinner()==0)
                    {
                        value = Math.min(value, 0);
                    }
                    if(test.checkwinner()==sp)
                    {
                        value = Math.min(value, depth);
                    }
                    if(test.checkwinner()==gsp)
                    {
                        value = Math.min(value, -depth);
                    }
                }
            }
        }
        return value;
    }

    public int aiTurn()
    {
        final int deepness=6;
        if(turn==0)
        {
            return 3;
        }

        int[]eval=new int[w];
        boolean[]use=new boolean[w];
        for(int j=0;j<w;j++)
        {
            ArrayList<Integer> hist=new ArrayList<Integer>();
            hist.add(j);
            try{
                recreate2(hist);
                eval[j]=minimax(false,hist,deepness);
                use[j]=true;
            }
            catch(outstandingMoveException e)
            {use[j]=false;}
        }

        ArrayList<Integer> whynot=new ArrayList<Integer>();
        for(int i=deepness;i>=-deepness;i--)
        {
            for(int j=0;j<w;j++)
            {
                if(use[j]&&eval[j]==i)
                {
                    whynot.add(j);
                }
            }
            if(whynot.size()!=0)
            {
                int num = (int)(Math.random() * whynot.size());
                return whynot.get(num);
            }
        }
        whynot=possible();
        int num = (int)(Math.random() * whynot.size());
        return whynot.get(num);
    }
}
