import java.util.*;

/**
 * Class for the AI of the connect four game
 */
class AiMiniMax extends VierLogik//implementation of the Minimax-KI
{
    private final int turn;//needed for choosing player
    private final int[][]game;
    private final int deepness;//searching deep
    /**
     * @param turn turn of connect four game
     */
    AiMiniMax(int[][]game,int turn)
    {
        this.turn=turn;
        this.game=game;
        double deep=5+(81-game.length*game[0].length)/16.0;
        deepness=(int)deep;
    }
    /**
     * @param turn turn of connect four game
     * @param deep depth of minimax search
     */
    AiMiniMax(int[][]game,int turn,int deep)//for simulations or exact values for deep
    {
        this.game=game;
        this.turn=turn;
        deepness=deep;
    }

    /**
     * @param further Field to simulate to set On
     * @return is the step further possible
     */
    private boolean legal(int further)//set On further possible when recreated
    {
        return setPos(game,further)!=-1;
    }

    private boolean legal(int[][]g,int further)//set On further possible when recreated
    {
        return setPos(g,further)!=-1;
    }

    /**
     * @return All possible Fields
     */
    private ArrayList<Integer> possible()//should never be used, but in case...
    {
        ArrayList<Integer> poss=new ArrayList<Integer>();
        for(int i=0;i<game.length;i++)
        {
            if(legal(i))
            {
                poss.add(i);
            }
        }
        return poss;
    }

    int[][] copy(int[][] g)
    {
        int[][] newg=new int[g.length][g[0].length];
        for(int i=0; i<g.length; i++)
        {
            for (int j = 0; j < g[0].length; j++)
            {
                newg[i][j] = g[i][j];
            }
        }
        return newg;
    }

    /**
     * @param maximizingPlayer max or min round
     * @param depth depth of the search
     * @return evaluated value
     */
    private int minimax(boolean maximizingPlayer, int[][] game, int depth)//main minimax-algorithm
    {
        if(depth==0)//max deepness is 0 for a tie
        {
            return 0;
        }
        int value;
        final int sp=turn%2+1;//player whom is the AI
        final int gsp=(1+turn)%2+1;//opposite player
        //VierLogik test;//for simulation
        if(maximizingPlayer)//good for the AI so the maximum of the values
        {
            value = -deepness-1;//theoretical
            for(int i=0;i<game.length;i++)//for each field
            {
                try//if set not possible: theoretical value
                {
                    int[][] testGame=setOn(copy(game),turn+deepness-depth+1,i);
                    if(checkwinner(testGame)==-1)//next Turn interesting
                    {
                        if(depth-1==0)
                        {
                            value = Math.min(value, 0);
                        }
                        else
                        {
                            value = Math.max(value, minimax(false, testGame, depth - 1));//false because after your turn is the opponent's turn
                            //depth-1 to make it end sometime
                        }
                    }
                    if(checkwinner(testGame)==0)//tie
                    {
                        value = Math.max(value, 0);
                    }
                    if(checkwinner(testGame)==sp)//win
                    {
                        return depth;
                    }
                    if(checkwinner(testGame)==gsp)//defeat (should never be the case)
                    {
                        value = Math.max(value, -depth);
                    }
                }
                catch(NoFreeFieldException e){}

            }
        }
        else//not good: perfect turn for opponent
        {
            value = deepness+1;//theoretical
            for(int i=0;i<game.length;i++)//for each field
            {
                try//if set not possible: theoretical value
                {
                    int[][] testGame=setOn(copy(game),turn+deepness-depth+1,i);
                    if(checkwinner(testGame)==-1)//next Turn interesting
                    {
                        if(depth-1==0)
                        {
                            value = Math.min(value, 0);
                        }
                        else {
                            value = Math.min(value, minimax(true, testGame, depth - 1));//true because after that is your turn
                            //depth-1 to make it end sometime
                        }
                    }
                    if(checkwinner(testGame)==0)//tie
                    {
                        value = Math.min(value, 0);
                    }
                    if(checkwinner(testGame)==sp)// win (should never be the case)
                    {
                        value = Math.min(value, depth);
                    }
                    if(checkwinner(testGame)==gsp)//defeat
                    {
                        return -depth;
                    }
                }
                catch(NoFreeFieldException e){}
            }
        }
        return value;//evaluated value for game situation
    }

    /**
     * interface for outside
     * @return Turn AI has decided to make
     */
    int aiTurn()//only method to be used from outside
    {
        int[]eval=new int[game.length];//store the values for setting on field 0 to w-1
        boolean[]use=new boolean[game.length];//true: use value in eval
        //false: don't use value in eval
        for(int j=0;j<game.length;j++)
        {
            try
            {
                if (checkwinner(setOn(copy(game), turn, j)) != -1)//instant win (checkwinner should never be the opposite player)
                {
                    return j;
                }
            }
            catch(NoFreeFieldException e){}

            use[j]=legal(j);//decide whether to use or not
            if(use[j])
            {
                try
                {
                    eval[j] = minimax(false, setOn(copy(game), turn, j), deepness);//using minimax to assign value to each possible field to set on
                }
                catch(NoFreeFieldException e){}
            }
        }

        ArrayList<Integer> whynot=new ArrayList<Integer>();
        for(int i=deepness;i>=-deepness;i--)//i value of deepness would be best
            //0 would be a tie or max deepness reached
            //values below 0 are a win of the opposite
            //the deeper the faster the defeat
        {
            for(int j=0;j<game.length;j++)
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
