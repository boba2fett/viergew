import java.util.*;
public class Minimax
{
    public Minimax()
    {
    }

    public int minimax(Node node,int depth,boolean maximizingPlayer)
    {
        int value;
        if(depth == 0 || node.isTerminal())
        {
            return node.value();
        }
        if(maximizingPlayer)
        {
            value = -1;
            for(Node child:node.childnodes())
            {
                value = Math.max(value, minimax(child,depth-1, false));
            }
        }
        else
        {
            value = 1;
            for(Node child:node.childnodes())
            {
                value = Math.min(value, minimax(child,depth-1, true));
            }
        }
        return value;
    }
}
