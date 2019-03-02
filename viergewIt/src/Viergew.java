import java.util.Arrays;

public class Viergew
{
    int[][] game = new int[7][6];
    int turn = 0;

    public Viergew()
    {
        for (int x = 0; x < game.length; x++)
        {
            Arrays.fill(game[x], 0);
        }
    }

    public void setOn(int num)
    {
        int pos=setPos(num);
        if(pos!=-1)
        {
            game[num][pos] = turn % 2 + 1;
            turn++;
        }
    }

    public int setPos(int num)
    {
        if (num < 0 || num > 6)
        {
            return -1;
        }
        boolean wasZero=false;
        for (int i = 0; i < game[num].length; i++)
        {
            if(wasZero&&game[num][i]!= 0)
            {
                return i-1;
            }
            wasZero=(game[num][i] == 0);
        }
        if(wasZero)
        {
            return game[num].length-1;
        }
        return -1;
    }

    public int checkwinner()
    {
        for (int sp = 1; sp <= 2; sp++)
        {
            for (int x = 0; x < game.length; x++)
            {
                for (int y = 0; y < game[x].length; y++)
                {
                    if (checkLines(x, y, sp)||checkRows(x, y, sp)||checkBackslash(x, y, sp)||checkSlash(x, y, sp))
                    {
                        return sp;
                    }
                }
            }
        }
        if(turn==42)
        {
            return 0;
        }
        return -1;
    }

    private boolean checkLines(int x, int y, int sp)
    {
        if (x + 4 > game.length)
        {
            return false;
        }
        for (int i = 0; i < 4; i++)
        {
            if (game[x + i][y] != sp)
            {
                return false;
            }
        }
        return true;
    }

    private boolean checkRows(int x, int y, int sp)
    {
        if (y + 4 > game[x].length)
        {
            return false;
        }
        for (int i = 0; i < 4; i++)
        {
            if (game[x][y + i] != sp)
            {
                return false;
            }
        }
        return true;
    }

    private boolean checkSlash(int x, int y, int sp)
    {
        if (x + 4 > game.length || y + 4 > game[x].length)
        {
            return false;
        }
        for (int i = 0; i < 4; i++)
        {
            if (game[x + i][y + i] != sp)
            {
                return false;
            }
        }
        return true;
    }

    private boolean checkBackslash(int x, int y, int sp)
    {
        if (x + 4 > game.length || y - 3 < 0)
        {
            return false;
        }
        for (int i = 0; i < 4; i++)
        {
            if (game[x + i][y - i] != sp)
            {
                return false;
            }
        }
        return true;
    }

    public void out()
    {
        for (int y = 0; y < game[0].length ; y++)
        {
            for (int x = 0; x < game.length; x++)
            {
                System.out.print(game[x][y]);
            }
            System.out.println();
        }
    }
}
