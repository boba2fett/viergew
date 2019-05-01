import java.util.*;
class AiMiniMax
{
    private final int turn;
    private final ArrayList<Integer> history;
    private final int w;
    private final int h;
    final int deepness;

    AiMiniMax(int turn,ArrayList<Integer> history,int w,int h)
    {
        this.turn=turn;
        this.history=history;
        this.w=w;
        this.h=h;
        int deep=7;
        switch(w)
        {
            case 4:
                deep=h>6?9:10;
                break;
            case 5:
                deep=h>6?8:9;
                break;
            case 6:
                deep=h>6?7:8;
                break;
            case 7:
                deep=h>6?6:7;
                break;
            case 8:
                deep=h>6?6:6;
                break;
            case 9:
                deep=h>6?6:6;
                break;

        }
        deepness=deep;
    }

    AiMiniMax(int turn,ArrayList<Integer> history,int w,int h,int deep)
    {
        this.turn=turn;
        this.history=history;
        this.w=w;
        this.h=h;
        deepness=deep;
    }

    private boolean legal(int further)
    {
        VierLogik vier=new VierLogik(w,h);
        for (int num : history)
        {
            vier.setOn(num);
        }
        return vier.setOn(further);
    }

    private VierLogik recreate(ArrayList<Integer> further)
    {
        VierLogik vier=new VierLogik(w,h);
        for (int num : history)
        {
            vier.setOn(num);
        }
        for (int num : further)
        {
            vier.setOn(num);
        }
        return vier;
    }

    private VierLogik recreate(int further)
    {
        VierLogik vier=new VierLogik(w,h);
        for (int num : history)
        {
            vier.setOn(num);
        }
        vier.setOn(further);

        return vier;
    }

    private ArrayList<Integer> possible()
    {
        ArrayList<Integer> poss=new ArrayList<Integer>();
        for(int i=0;i<w;i++)
        {
            if(legal(i))
            {
                poss.add(i);
            }
        }
        return poss;
    }

    private int minimax(boolean maximizingPlayer, ArrayList<Integer> hist, int depth)
    {
        if(depth==0)
        {
            return 0;
        }
        int value;
        final int sp=turn%2+1;
        final int gsp=(1+turn)%2+1;
        VierLogik test;
        if(maximizingPlayer)
        {
            value = -43;//theoretical

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
            value = 43;//theoretical
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

    int aiTurn()
    {
        int[]eval=new int[w];
        boolean[]use=new boolean[w];
        for(int j=0;j<w;j++)
        {
            if(recreate(j).checkwinner()!=-1)
            {
                return j;
            }
            use[j]=legal(j);
            if(use[j])
            {
                ArrayList<Integer> hist=new ArrayList<Integer>();
                hist.add(j);
                eval[j]=minimax(false,hist,deepness);
            }
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
