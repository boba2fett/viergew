import java.util.*;
public class Game2
{
    Viergew vier;
    public static void main(String args[])
    {
        Game2 g=new Game2();
        g.start();
    }

    public Game2()
    {
        vier=new Viergew();
    }

    public void start()
    {
        do{
            vier=new Viergew();
            do{
                if(!vier.setOn(vier.ai()))
                {
                    System.out.println("FAIL");
                    return;
                }
            }while(vier.checkwinner()==-1);
            if(vier.checkwinner()==0)
            {
                System.out.println("Unentschieden");
            }
            else
            {
                System.out.println("Spieler "+vier.checkwinner()+" hat gewonnen");
            }
        }while(true);

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
