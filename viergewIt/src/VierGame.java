import java.util.*;
/**
 *extended VierLogik
 * adds Interface for ai and stores history
 */
public class VierGame extends VierLogik
{
    int game[][];
    int turn=0;
    AiMiniMaxSave a=new AiMiniMaxSave();
    int last;

    VierGame(int w,int h)
    {
        game=new int[w][h];
    }

    public int setPos(int num)
    {
        return super.setPos(game,num);
    }

    public int checkwinner()
    {
        return super.checkwinner(game);
    }

    /**
     * @param num Field to set on
     * @return true Success
     */
    public boolean setOn(int num)
    {
        int pos=setPos(game,num);
        if(pos!=-1)
        {
            game[num][pos] = turn % 2 + 1;
            turn++;
            last=num;
            return true;
        }
        return false;
    }

    /**
     * Output for console game
     */
    void out()//output for console game
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

    /**Ai Interface
     * @return Field AI wants to set on
     */
    int ai()
    {
        //AiMiniMaxPrint a=new AiMiniMaxPrint(turn,(ArrayList<Integer>)history.clone(),game.length,game[0].length);//give AI all important information
        //give AI all important information
        return a.init(game,turn,2,last);
    }

    /**
     * @return total turns of the Game
     */
    int getTurns()
    {
        return turn;
    }

    /**Where was the win
     * @return coordinates of the winning 4 Fields
     */
    int[][] winwhere()//where has been won
    {
        int[][] arr=new int[4][2];//4 x:y
        //x1:y1; x2:y2; x3:y3; x4:y4;
        if(checkwinner(game)>0)
        {
            for (int sp = 1; sp <= 2; sp++)
            {
                for (int x = 0; x < game.length; x++)
                {
                    for (int y = 0; y < game[x].length; y++)
                    {
                        if (checkLines(game,x, y, sp))//win was line -
                        {
                            for (int i = 0; i < 4; i++)
                            {
                                arr[i][0]=x+i;
                                arr[i][1]=y;
                            }
                        }
                        if(checkRows(game,x, y, sp))//win was row |
                        {
                            for (int i = 0; i < 4; i++)
                            {
                                arr[i][0]=x;
                                arr[i][1]=y + i;
                            }
                        }
                        if(checkBackslash(game,x, y, sp))//win was backslash \
                        {
                            for (int i = 0; i < 4; i++)
                            {
                                arr[i][0]=x + i;
                                arr[i][1]=y - i;
                            }
                        }
                        if(checkSlash(game,x, y, sp))//win was slash /
                        {
                            for (int i = 0; i < 4; i++)
                            {
                                arr[i][0]=x + i;
                                arr[i][1]=y + i;
                            }
                        }

                    }
                }
            }
        }
        return arr;
    }
}
