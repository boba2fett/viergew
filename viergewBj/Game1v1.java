import java.util.*;
public class Game1v1
{
    Viergew vier;
    public static void main(String args[])
    {
        Game1v1 g=new Game1v1();
        g.start();
    }

    public Game1v1()
    {
        vier=new Viergew();
    }

    public void start()
    {
        System.out.println("Willkommen\n('x' beendet immer)");
        do{
            vier.out();
            System.out.println("0123456");
            //System.out.println("ai: "+vier.ai());//Ai hints
            int num;
            do{
                System.out.println(vier.getTurns()%2+1==1?'X':'O');
                num=numIn("Worauf setzen?");
            }while(vier.setPos(num)==-1);
            vier.setOn(num);
            System.out.println();
        }while(vier.checkwinner()==-1);
        vier.out();
        System.out.println("0123456");
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
            if(in.indexOf('x')!=-1)
            {
                System.out.println("Bye Bye!");
                System.exit(0);
            }
        }while(!isNum(in));
        int num=Integer.parseInt(in);
        return num;
    }
}
