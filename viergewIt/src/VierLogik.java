/**
 * Simple connect-4 game
 */
public class VierLogik
{
    /**
     * Game field
     */
    int[][] game;//game array
    /**
     * Turn counter
     */
    int turn = 0;//turn count

    /**
     * @param w Width of Game Field
     * @param h Height of Game Field
     */
    VierLogik(int w,int h)//custom field
    {
        game = new int[w][h];
    }
    /**
     * @param num Field to set On
     * @return true Success
     */
    public boolean setOn(int num)//set on a given number
    {
        int pos=setPos(num);
        if(pos!=-1)//if possible
        {
            game[num][pos] = turn % 2 + 1;
            turn++;
            return true;//it worked
        }
        return false;//it didn't worked
    }
    /**
     * @param num Field to set on
     * @return Which position in the array to use when set on num
     */
    int setPos(int num)//calculate y-position for given x when set on
    {
        if (num < 0 || num > game.length-1)//not in range
        {
            return -1;
        }
        boolean wasZero=false;//until there is a zero it should fall down (higher numbers in the array)
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
        return -1;//if no zero in row=> not possible
    }

    /**
     * @return Check for any winner. -1 is no winner
     */
    int checkwinner()//check who has won
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
        if(nozero())//when there is no zero and no winner => a tie
        {
            return 0;
        }
        return -1;
    }

    /**
     * @return is the Field full/no zeros on the whole array
     */
    private boolean nozero()//check for a full field
    {
        for(int i=0;i<game.length;i++)
        {
            if(setPos(i)!=-1)
            {
                return false;
            }
        }
        return true;
    }
//checking for 4 in a row
    /**
     * @return checking for 4 in a line
     */
    boolean checkLines(int x, int y, int sp)// -
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

    /**
     * @return checking for 4 in a column
     */
    boolean checkRows(int x, int y, int sp)// |
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
    /**
     * @return checking for 4 in a slash form
     */
    boolean checkSlash(int x, int y, int sp)// /
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
    /**
     * @return checking for 4 in a backslash form
     */
    boolean checkBackslash(int x, int y, int sp)// \
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
