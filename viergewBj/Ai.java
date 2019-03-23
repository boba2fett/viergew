import java.util.*;
public class Ai
{
    final int turn;
    final ArrayList<Integer> history;
    public Ai(int pturn,ArrayList<Integer> phistory)
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

    public Viergew recreate()
    {
        Viergew vier=new Viergew();
        for (int num : history)
        {
            vier.setOn(num);
        }
        return vier;
    }

    public int aiTurn()
    {
        final int sp=turn%2+1;
        ArrayList<Integer> watch=turn(sp);
        if(!watch.isEmpty())
        {
            int num = (int)(Math.random() * watch.size()); 
            return watch.get(num);
        }
        ArrayList<Integer> dont=nextTurnThreats(sp);
        //System.out.println(dont);
        ArrayList<Integer> poss=possible();
        ArrayList<Integer> whynot=new ArrayList<Integer>();
        for(int num:poss)
        {
            if(dont.indexOf(num)==-1)
            {
                whynot.add(num);
            }
        }
        if(!whynot.isEmpty())
        {
            int num = (int)(Math.random() * whynot.size()); 
            return whynot.get(num);
        }
        int num = (int)(Math.random() * poss.size()); 
        return poss.get(num);
    }

    private ArrayList<Integer> turn(int sp)
    {
        ArrayList<Integer> winchoice=new ArrayList<Integer>();
        Viergew test;
        for(int i=0;i<7;i++)
        {
            test=recreate();
            test.setOn(i);
            if(test.checkwinner()==sp)
            {
                winchoice.add(i);
            }
        }
        return winchoice;
    }

    private ArrayList<Integer> nextTurnThreats(final int sp)
    {
        ArrayList<Integer> threats=new ArrayList<Integer>();
        Viergew test;
        for(int i=0;i<7;i++)
        {
            for(int j=0;j<7;j++)
            {
                test=recreate(i);
                test.setOn(j);
                if(test.checkwinner()==1||test.checkwinner()==2)
                {
                    threats.add(i);
                }
            }
        }
        return threats;
    }

    private ArrayList<Integer> possible()
    {
        ArrayList<Integer> poss=new ArrayList<Integer>();
        Viergew test;
        for(int i=0;i<7;i++)
        {
            test=recreate();
            if(test.setOn(i))
            {
                poss.add(i);
            }
        }
        return poss;
    }

    private ArrayList<Integer> possible(ArrayList<Integer> hist)
    {
        ArrayList<Integer> poss=new ArrayList<Integer>();
        Viergew test;
        for(int i=0;i<7;i++)
        {
            test=recreate(hist);
            if(test.setOn(i))
            {
                poss.add(i);
            }
        }
        return poss;
    }

    private ArrayList<Integer> nextTurnsDont(final ArrayList<Integer> hist,ArrayList<Integer> ignore)
    {
        Viergew v=recreate(hist);
        if(v.checkwinner()==0||v.checkwinner()==turn%2+1)
        {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> threats=new ArrayList<Integer>();
        ArrayList<Integer> poss=possible(hist);
        for(int i:poss)
        {
            if(ignore.indexOf(i)!=-1)
            {
                Viergew test;
                for(int j=0;j<7;j++)
                {
                    ArrayList<Integer>hist2=hist;
                    hist2.add(i);
                    test=recreate();
                    test.setOn(j);
                    if(test.checkwinner()==(turn+1)%2+1)
                    {
                        threats.add(i);
                    }
                }

            }
        }

        return threats;
    }
}
