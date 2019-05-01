import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameGui extends JFrame {

    public static void main(String[] args) {
        new GameGui();        //main method and instantiating tic tac object and calling initialize function
    }

    private VierGame v;
    private boolean ready=false;
    private int players;
    private int w;
    private int h;
    private int ki=0;

    private JFrame frame = new JFrame("VierGewinnt");

    private JButton[][] fields;

    //private JButton player0 = new JButton("0 Spieler");
    private JButton player1ki1 = new JButton("1 Spieler KI ist 1");
    private JButton player1ki2 = new JButton("1 Spieler KI ist 2");
    private JButton player2 = new JButton("2 Spieler");//buttons for decisions

    private JButton again = new JButton("Nochmal");
    private JButton reset = new JButton("Reset");

    private JPanel jout = new JPanel(new GridLayout());
    //private JTextField tf = new JTextField();//output for text
    private JLabel jl = new JLabel("");

    private JPanel mainPanel = new JPanel(new BorderLayout());//create main panel container to put layer others on top
    private JPanel menu;//panel for buttons for decisions
    private JPanel game;//Create two more panels with layouts for buttons

    private JButton[][] buttons;

    private GameGui() {
        super();
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);        //Setting dimension of JFrame and setting parameters
        frame.setVisible(true);
        frame.setResizable(true);
        initialize();//for buttons and so on
    }

    private void initialize()             //Initialize gui
    {
        frame.add(mainPanel);                                         //add main container panel to frame
        mainPanel.setPreferredSize(new Dimension(1000, 1000));//Setting dimensions of panels
        jout.setPreferredSize(new Dimension(1000, 100));
        //mainPanel.add(menu, BorderLayout.NORTH);                   //Add three panels to the main container panel
        mainPanel.add(jout, BorderLayout.CENTER);

        jout.add(jl);//output for text in own panel




        int w=6;
        int h=6;
        menu= new JPanel(new GridLayout(h, w));
        menu.setPreferredSize(new Dimension(1000, 1000));
        fields=new JButton[w][h];

        for (int y = 0;y < h; y++)                //Create grid
        {
            for (int x = 0; x < w; x++)
            {
                fields[x][y] = new JButton();                //Creating buttons
                menu.add(fields[x][y]);
                fields[x][y].setText(" ");//is better for layout however
                fields[x][y].setText(""+(x+4)+"x"+(y+4));
                fields[x][y].setVisible(true);
                fields[x][y].setEnabled(true);
                fields[x][y].addActionListener(new myActionListener());        //Adding response event to buttons
                fields[x][y].setPreferredSize(new Dimension(10, 10));
            }
        }
        mainPanel.add(menu, BorderLayout.NORTH);

        //player0.addActionListener(new myActionListener());//playerCount
        player1ki1.addActionListener(new myActionListener());
        player1ki2.addActionListener(new myActionListener());
        player2.addActionListener(new myActionListener());

        reset.addActionListener(new myActionListener());
        again.addActionListener(new myActionListener());
        //setToolTipText("Hilfe");

        /**/
    }

    private void resetMenu() {//clear menu to add new things
        menu.setVisible(false);
        menu = new JPanel(new GridLayout(1, 3));
        menu.setPreferredSize(new Dimension(1000, 100));
        mainPanel.add(menu, BorderLayout.NORTH);
    }

    private void playersChoosen1()
    {
        resetMenu();
        ready=true;
        menu.add(reset);
        jl.setText(v.getTurns()%2+1==1?"X":"O");
        if(players==0)
        {
            autogame();
        }
        else
        {
            game();
        }
    }

    private void fieldChoosen0()
    {
        v= new VierGame(w,h);
        game=new JPanel(new GridLayout(h, w));
        game.setPreferredSize(new Dimension(1000, 800));
        mainPanel.add(game, BorderLayout.SOUTH);

        buttons=new JButton[w][h];

        for (int y = 0;y < h; y++)                //Create grid
        {
            for (int x = 0; x < w; x++)
            {
                buttons[x][y] = new JButton();                //Creating buttons
                game.add(buttons[x][y]);
                buttons[x][y].setText(" ");//is better for layout however
                buttons[x][y].setText(""+x+":"+y);
                buttons[x][y].setVisible(true);
                buttons[x][y].setEnabled(false);
                buttons[x][y].addActionListener(new myActionListener());        //Adding response event to buttons
                buttons[x][y].setPreferredSize(new Dimension(10, 10));
            }
        }

        resetMenu();
        //menu.add(player0);
        menu.add(player1ki1);                //Add buttons to menu
        menu.add(player1ki2);
        menu.add(player2);

    }

    private class myActionListener implements ActionListener {      //Implementing action listener for buttons
        public void actionPerformed(ActionEvent a) {

            if(a.getSource()==reset)
            {
                reset();
            }

            if(a.getSource()==again)
            {
                again();
            }

            if(ready) {
                for (int y = 0;y < h; y++)                //Create grid
                {
                    for (int x = 0; x < w; x++)
                    {
                        if(a.getSource()==buttons[x][y])
                        {
                            game(x);
                        }
                    }
                }

            }
            else
            {
                for (int y = 0;y < fields[0].length; y++)                //Create grid
                {
                    for (int x = 0; x < fields.length; x++)
                    {
                        if(a.getSource()==fields[x][y])
                        {
                            w=x+4;
                            h=y+4;
                            fieldChoosen0();
                        }
                    }
                }

                /*if (a.getSource() == player0) {
                    players=0;
                    playersChoosen1();
                }*/
                if (a.getSource() == player1ki1) {
                    players=1;
                    ki=1;
                    playersChoosen1();
                }
                if (a.getSource() == player1ki2) {
                    players=1;
                    ki=2;
                    playersChoosen1();
                }
                if (a.getSource() == player2) {
                    players=2;
                    playersChoosen1();
                }
            }
        }

    }

    private void reset()
    {
        ki=0;
        ready=false;
        game.setVisible(false);

        menu.setVisible(false);
        int w=6;
        int h=6;
        menu= new JPanel(new GridLayout(h, w));
        menu.setPreferredSize(new Dimension(1000, 1000));
        fields=new JButton[w][h];

        for (int y = 0;y < h; y++)                //Create grid
        {
            for (int x = 0; x < w; x++)
            {
                fields[x][y] = new JButton();                //Creating buttons
                menu.add(fields[x][y]);
                fields[x][y].setText(" ");//is better for layout however
                fields[x][y].setText(""+(x+4)+"x"+(y+4));
                fields[x][y].setVisible(true);
                fields[x][y].setEnabled(true);
                fields[x][y].addActionListener(new myActionListener());        //Adding response event to buttons
                fields[x][y].setPreferredSize(new Dimension(10, 10));
            }
        }
        mainPanel.add(menu, BorderLayout.NORTH);
        jl.setText("");
    }

    private void again()
    {
        game.setVisible(false);
        v= new VierGame(w,h);
        game=new JPanel(new GridLayout(h, w));
        game.setPreferredSize(new Dimension(1000, 800));
        mainPanel.add(game, BorderLayout.SOUTH);

        buttons=new JButton[w][h];

        for (int y = 0;y < h; y++)                //Create grid
        {
            for (int x = 0; x < w; x++)
            {
                buttons[x][y] = new JButton();                //Creating buttons
                game.add(buttons[x][y]);
                buttons[x][y].setText(" ");//is better for layout however
                buttons[x][y].setText(""+x+":"+y);
                buttons[x][y].setVisible(true);
                buttons[x][y].setEnabled(false);
                buttons[x][y].addActionListener(new myActionListener());        //Adding response event to buttons
                buttons[x][y].setPreferredSize(new Dimension(10, 10));
            }
        }

        resetMenu();
        ready=true;
        menu.add(reset);
        jl.setText(v.getTurns()%2+1==1?"X":"O");
        if(players==0)
        {
            autogame();
        }
        else
        {
            game();
        }
    }

    private void block()
    {
        ready=false;
        for (int y = 0;y < h; y++)                //Create grid
        {
            for (int x = 0; x < w; x++)
            {
                buttons[x][y].setEnabled(false);
            }
        }
        menu.add(again);
    }

    private void game()
    {
        jl.setText(v.getTurns()%2+1==1?"X":"O");
        for (int x = 0; x < w; x++)
        {
            if(v.setPos(x)!=-1)
            {
                buttons[x][v.setPos(x)].setEnabled(true);
            }
        }

        if(v.getTurns()%2+1==ki)
        {
            game(v.ai());
        }
    }

    private void game(int num)
    {
        int y=v.setPos(num);
        buttons[num][y].setEnabled(false);
        buttons[num][y].setText(v.getTurns()%2+1==1?"X":"O");
        buttons[num][y].setBackground(v.getTurns()%2+1==1?Color.RED:Color.BLACK);
        v.setOn(num);
        int win=v.checkwinner();
        if(win==0)
        {
            jl.setText("Unentschieden");
            block();
            return;
        }
        if(win>0)
        {
            jl.setText("Spieler "+(win==1?"X":"O")+" hat gewonnen");
            markWin();
            block();
            return;
        }
        game();
    }

    private void autogame()
    {
        while(v.checkwinner()==-1)
        {
            int num=v.ai();
            int y=v.setPos(num);
            buttons[num][y].setEnabled(false);
            buttons[num][y].setText(v.getTurns()%2+1==1?"X":"O");
            buttons[num][y].setBackground(v.getTurns()%2+1==1?Color.RED:Color.BLACK);
            v.setOn(num);
        }
        int win=v.checkwinner();
        if(win==0)
        {
            jl.setText("Unentschieden");
            block();
        }
        if(win>0)
        {
            jl.setText("Spieler "+(win==1?"X":"O")+" hat gewonnen");
            markWin();
            block();
        }
    }

    private void markWin()
    {
        int arr[][]=v. winwhere();
        for(int i=0;i<arr.length;i++)
        {
                buttons[arr[i][0]][arr[i][1]].setBackground(Color.YELLOW);
        }
    }
}