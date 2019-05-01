import java.util.ArrayList;

public class Testing
{
    public static void main(String args[]) {
        for(int w=4;w<10;w++)
        {
            System.out.println("wwww "+w);
            for(int h=4;h<10;h++)
            {
                System.out.println("hhhhh "+h);
                for(int d=1;d<16-w;d++)
                {
                    System.out.println("dddddd "+d);
                    final long timeStart = System.currentTimeMillis();
                    AiMiniMax a=new AiMiniMax(0,new ArrayList<Integer>(),w,h,d);
                    int t=a.aiTurn();
                    final long timeEnd = System.currentTimeMillis();
                    double time=(timeEnd - timeStart)/1000.0;
                    System.out.println("w "+w+" h "+h+" d "+d+" : " +time+"");
                    System.out.println(t);
                }
            }
        }
    }
}
