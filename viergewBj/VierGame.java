import java.util.*;
public class VierGame extends VierLogik
{
    ArrayList<Integer> history=new ArrayList<Integer>();
    public VierGame()
    {
        
    }

    public boolean setOn(int num)
    {
        int pos=setPos(num);
        if(pos!=-1)
        {
            history.add(num);
            game[num][pos] = turn % 2 + 1;
            turn++;
            return true;
        }
        return false;
    }

    public void out()
    {
        for (int y = 0; y < game[0].length ; y++)
        {
            for (int x = 0; x < game.length; x++)
            {
                System.out.print(game[x][y]==0?'-':game[x][y]==1?'X':'O');
            }
            System.out.println();
        }
    }

    public int ai()
    {
        AiMinimax a=new AiMinimax(turn,(ArrayList<Integer>)history.clone(),w);
        return a.aiTurn();
    }

    public int getTurns()
    {
        return turn;   
    }

    public int getHeight()
    {
        return h;   
    }

    public int getWidth()
    {
        return w;
    }
}
