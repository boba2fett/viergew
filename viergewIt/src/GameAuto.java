public class GameAuto
{
    private VierGame vier;
    public static void main(String args[])
    {
        GameAuto g=new GameAuto();
        g.start();
    }

    private GameAuto()
    {
        vier=new VierGame();
    }

    private void start()
    {
        int wins1=0;
        int wins2=0;
        for(int round=0;round<10;round++)
        {
            vier=new VierGame();
            System.out.println("Runde "+round);
            do{
                int num;
                do{
                    num=vier.ai();
                }while(vier.setPos(num)==-1);
                vier.setOn(num);
            }while(vier.checkwinner()==-1);
            vier.out();
            if(vier.checkwinner()==0)
            {
                System.out.println("Unentschieden");
            }
            else
            {
                System.out.println("Win von "+vier.checkwinner());
                if(vier.checkwinner()==1)
                {wins1++;}
                if(vier.checkwinner()==2)
                {wins2++;}
            }
            System.out.println("---- Sp1 "+wins1+" Sp2 "+wins2);
        }
    }
}
