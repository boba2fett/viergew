import java.util.*;
class AiMiniMax//implementation of the Minimax-KI
{
    private final int turn;//needed for choosing player
    private final ArrayList<Integer> history;//needed for recreation
    private final int w;//width
    private final int h;//height
    private final int deepness;//searching deep

    AiMiniMax(int turn,ArrayList<Integer> history,int w,int h)
    {
        this.turn=turn;
        this.history=history;
        this.w=w;
        this.h=h;
        int deep=7;
        switch(w)//decision based on simulations
        {
            case 4:
                switch(h)
                {
                    case 4:
                        deep=11;
                        break;
                    case 5:
                        deep=10;
                        break;
                    case 6:
                        deep=10;
                        break;
                    case 7:
                        deep=10;
                        break;
                    case 8:
                        deep=10;
                        break;
                    case 9:
                        deep=10;
                        break;

                }
                break;
            case 5:
                switch(h)
                {
                    case 4:
                        deep=9;
                        break;
                    case 5:
                        deep=9;
                        break;
                    case 6:
                        deep=9;
                        break;
                    case 7:
                        deep=8;
                        break;
                    case 8:
                        deep=8;
                        break;
                    case 9:
                        deep=8;
                        break;

                }
                break;
            case 6:
                switch(h)
                {
                    case 4:
                        deep=8;
                        break;
                    case 5:
                        deep=8;
                        break;
                    case 6:
                        deep=7;
                        break;
                    case 7:
                        deep=7;
                        break;
                    case 8:
                        deep=7;
                        break;
                    case 9:
                        deep=7;
                        break;

                }
                break;
            case 7:
                switch(h)
                {
                    case 4:
                        deep=7;
                        break;
                    case 5:
                        deep=7;
                        break;
                    case 6:
                        deep=7;
                        break;
                    case 7:
                        deep=7;
                        break;
                    case 8:
                        deep=7;
                        break;
                    case 9:
                        deep=7;
                        break;

                }
                break;
            case 8:
                deep=6;
                /*switch(h)
                {
                    case 4:
                        deep=6;
                        break;
                    case 5:
                        deep=6;
                        break;
                    case 6:
                        deep=6;
                        break;
                    case 7:
                        deep=6;
                        break;
                    case 8:
                        deep=6;
                        break;
                    case 9:
                        deep=6;
                        break;

                }*/
                break;
            case 9:
                deep=6;
                break;

        }
        deepness=deep;
    }

    AiMiniMax(int turn,ArrayList<Integer> history,int w,int h,int deep)//for simulations or exact values for deep
    {
        this.turn=turn;
        this.history=history;
        this.w=w;
        this.h=h;
        deepness=deep;
    }

    private boolean legal(int further)//set On further possible when recreated
    {
        VierLogik vier=new VierLogik(w,h);
        for (int num : history)
        {
            vier.setOn(num);
        }
        return vier.setOn(further);
    }

    private VierLogik recreate(ArrayList<Integer> further)//recreate a Game with addition of the turns in further
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

    private VierLogik recreate(int further)//recreate a Game with addition of further
    {
        VierLogik vier=new VierLogik(w,h);
        for (int num : history)
        {
            vier.setOn(num);
        }
        vier.setOn(further);

        return vier;
    }

    private ArrayList<Integer> possible()//should never be used, but in case...
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

    private int minimax(boolean maximizingPlayer, ArrayList<Integer> hist, int depth)//main minimax-algorithm
    {
        if(depth==0)//max deepness is 0 for a tie
        {
            return 0;
        }
        int value;
        final int sp=turn%2+1;//player whom is the AI
        final int gsp=(1+turn)%2+1;//opposite player
        VierLogik test;//for simulation
        if(maximizingPlayer)//good for the AI so the maximum of the values
        {
            value = -deepness-1;//theoretical

            for(int i=0;i<w;i++)//for each field
            {
                test=recreate(hist);//recreate the field of the game

                if(test.setOn(i))//if set not possible value -43
                {
                    if(test.checkwinner()==-1)//next Turn interesting
                    {
                        ArrayList<Integer>hist2=(ArrayList<Integer>)hist.clone();
                        hist2.add(i);//add the number which was set on
                        value = Math.max(value, minimax( false,hist2,depth-1));//false because after your turn is the opponent's turn
                        //depth-1 to make it end sometime
                    }
                    if(test.checkwinner()==0)//tie
                    {
                        value = Math.max(value, 0);
                    }
                    if(test.checkwinner()==sp)//win
                    {
                        value = Math.max(value, depth);
                    }
                    if(test.checkwinner()==gsp)//defeat (should never be the case)
                    {
                        value = Math.max(value, -depth);
                    }
                }
            }
        }
        else//not good: perfect turn for opponent
        {
            value = deepness+1;//theoretical
            for(int i=0;i<w;i++)//for each field
            {
                test=recreate(hist);//recreate the field of the game
                if(test.setOn(i))//if set not possible value -43
                {
                    if(test.checkwinner()==-1)//next Turn interesting
                    {
                        ArrayList<Integer>hist2=(ArrayList<Integer>)hist.clone();
                        hist2.add(i);//add the number which was set on
                        value = Math.min(value, minimax( true,hist2,depth-1));//true because after that is your turn
                        //depth-1 to make it end sometime
                    }
                    if(test.checkwinner()==0)//tie
                    {
                        value = Math.min(value, 0);
                    }
                    if(test.checkwinner()==sp)// win (should never be the case)
                    {
                        value = Math.min(value, depth);
                    }
                    if(test.checkwinner()==gsp)//defeat
                    {
                        value = Math.min(value, -depth);
                    }
                }
            }
        }
        return value;//evaluated value for game situation
    }

    int aiTurn()//only method to be used from outside
    {
        int[]eval=new int[w];//store the values for setting on field 0 to w-1
        boolean[]use=new boolean[w];//true: use value in eval
        //false: don't use value in eval
        for(int j=0;j<w;j++)
        {
            if(recreate(j).checkwinner()!=-1)//instant win (checkwinner should never be the opposite player)
            {
                return j;
            }
            use[j]=legal(j);//decide whether to use or not
            if(use[j])
            {
                ArrayList<Integer> hist=new ArrayList<Integer>();
                hist.add(j);//first entry to add to recreations
                eval[j]=minimax(false,hist,deepness);//using minimax to assign value to each possible field to set on
            }
        }

        ArrayList<Integer> whynot=new ArrayList<Integer>();
        for(int i=deepness;i>=-deepness;i--)//i value of deepness would be best
            //0 would be a tie or max deepness reached
            //values below 0 are a win of the opposite
            //the deeper the faster the defeat
        {
            for(int j=0;j<w;j++)
            {
                if(use[j]&&eval[j]==i)
                {
                    whynot.add(j);
                }
            }
            if(whynot.size()!=0)//if sth. from eval matches i
            {
                int num = (int)(Math.random() * whynot.size());
                return whynot.get(num);//choose one of the options
            }
        }
        whynot=possible();//should never be used, but who knows ...
        int num = (int)(Math.random() * whynot.size());
        return whynot.get(num);
    }
}
