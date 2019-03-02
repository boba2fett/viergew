import java.util.*;
public class Game
{
    Viergew vier;
    public static void main(String args[])
    {
        Game g=new Game();
        g.start();
    }

    public Game()
    {
        vier=new Viergew();
    }

    public void start()
    {
        System.out.println("Willkomen");
        do{
            int num;
            do{
                num=numIn("Worauf setzen?");
            }while(vier.setPos(num)==-1);
            vier.setOn(num);
            System.out.println();
            vier.out();
            System.out.println();
        }while(vier.checkwinner()==-1);
        if(vier.checkwinner()==0)
        {
            System.out.println("Unentschieden");
        }
        else
        {
            System.out.println("Spieler "+vier.checkwinner()+" hat gewonnen");
        }
    }

    private boolean isNum(String num)
    {
        try{
            Integer.parseInt(num);
            return true;
        }catch(NumberFormatException e){
            System.out.println("bitte eine zahl verwenden");
            return false;
        }
    }

    private int numIn(String out)
    {
        Scanner scann=new Scanner(System.in);
        String in;
        System.out.println(out);
        do{
            in=scann.nextLine();
        }while(!isNum(in));
        int num=Integer.parseInt(in);
        return num;
    }
}
