import java.util.*;
public class Game4
{
    Viergew vier;
    public static void main(String args[])
    {
        Game4 g=new Game4();
        g.start();
    }

    public Game4()
    {
        vier=new Viergew();
    }

    public void start()
    {
        int wins1=0;
        int wins2=0;
        for(int i=0;i<10;i++)
        {
            vier=new Viergew();
            System.out.println("Willkomen");
            do{
                vier.out();
                System.out.println("0123456");
                int num;
                do{
                    System.out.println(vier.getTurns()%2+1==1?'X':'O');
                    num=vier.ai();
                    System.out.println("choose "+num);
                }while(vier.setPos(num)==-1);
                vier.setOn(num);
                System.out.println();
            }while(vier.checkwinner()==-1);
            if(vier.checkwinner()==0)
            {
                System.out.println("Unentschieden");
            }
            else
            {
                System.out.println("Spieler "+vier.checkwinner()+" hat gewonnen");
                if(vier.checkwinner()==1)
                {wins1++;}
                if(vier.checkwinner()==2)
                {wins2++;}
            }
        }
        System.out.println("Spieler1 "+wins1);
        System.out.println("Spieler2 "+wins2);
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
