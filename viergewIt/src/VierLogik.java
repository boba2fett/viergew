public class VierLogik
{
    final int w;
    final int h;
    int[][] game;
    int turn = 0;

    VierLogik()
    {
        this.w=7;
        this.h=6;
        game = new int[w][h];
    }

    VierLogik(int w,int h)
    {
        this.w=w;
        this.h=h;
        game = new int[w][h];
    }

    public boolean setOn(int num)
    {
        int pos=setPos(num);
        if(pos!=-1)
        {
            game[num][pos] = turn % 2 + 1;
            turn++;
            return true;
        }
        return false;
    }

    int setPos(int num)
    {
        if (num < 0 || num > w-1)
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

    int checkwinner()
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
        if(nozero())
        {
            return 0;
        }
        return -1;
    }

    boolean nozero()
    {
        for(int i=0;i<w;i++)
        {
            if(setPos(i)!=-1)
            {
                return false;
            }
        }
        return true;
    }

    boolean checkLines(int x, int y, int sp)
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

    boolean checkRows(int x, int y, int sp)
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

    boolean checkSlash(int x, int y, int sp)
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

    boolean checkBackslash(int x, int y, int sp)
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
}
