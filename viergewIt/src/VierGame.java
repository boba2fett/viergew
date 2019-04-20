import java.util.*;
public class VierGame extends VierLogik
{
    ArrayList<Integer> history=new ArrayList<Integer>();
    VierGame()
    {
        super();
    }

    VierGame(int w,int h)
    {
        super(w,h);
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

    void out()
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

    int ai()
    {
        AiMiniMax a=new AiMiniMax(turn,(ArrayList<Integer>)history.clone(),w,h);
        return a.aiTurn();
    }

    int getTurns()
    {
        return turn;
    }

    int[][] winwhere()
    {
        int[][] arr=new int[4][2];//4 x:y
        if(checkwinner()>0)
        {
            for (int sp = 1; sp <= 2; sp++)
            {
                for (int x = 0; x < game.length; x++)
                {
                    for (int y = 0; y < game[x].length; y++)
                    {
                        if (checkLines(x, y, sp))
                        {
                            for (int i = 0; i < 4; i++)
                            {
                                arr[i][0]=x+i;
                                arr[i][1]=y;
                            }
                        }
                        if(checkRows(x, y, sp))
                        {
                            for (int i = 0; i < 4; i++)
                            {
                                arr[i][0]=x;
                                arr[i][1]=y + i;
                            }
                        }
                        if(checkBackslash(x, y, sp))
                        {
                            for (int i = 0; i < 4; i++)
                            {
                                arr[i][0]=x + i;
                                arr[i][1]=y - i;
                            }
                        }
                        if(checkSlash(x, y, sp))
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
