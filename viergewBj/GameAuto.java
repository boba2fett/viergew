import java.util.*;
public class GameAuto
{
    Viergew vier;
    public static void main(String args[])
    {
        GameAuto g=new GameAuto();
        g.start();
    }

    public GameAuto()
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
}
