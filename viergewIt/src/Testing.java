import java.util.ArrayList;

public class Testing
{
    public static void main(String args[]) {
        int h=9;
        for(int w=5;w<=9;w+=2)
        {
            System.out.println("\nw:"+w);
            for(int d=1;d<10;d++)
            {
                final long timeStart = System.currentTimeMillis();
                AiMiniMax a=new AiMiniMax(0,new ArrayList<Integer>(),w,h,d);
                a.aiTurn();
                final long timeEnd = System.currentTimeMillis();
                double time=(timeEnd - timeStart)/1000.0;
                System.out.println("("+d+","+time+")");
            }
        }
    }
}
