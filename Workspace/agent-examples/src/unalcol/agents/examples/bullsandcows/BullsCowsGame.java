package unalcol.agents.examples.bullsandcows;

import java.io.*;
import unalcol.random.Random;

// Referenced classes of package unalcol.agents.examples.bullsandcows:
//            NumberIndex, SimpleBCPlayer

public class BullsCowsGame
{

    public BullsCowsGame()
    {
    }

    public static int read(String text)
    {
        String s;
        System.out.println(text);
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            s = bufferRead.readLine();
            return Integer.parseInt(s);
        }catch(IOException e){
            e.printStackTrace();
            return 0;
        }
    }

    public static int[] readNumber(String text)
    {
        int opt[];
        System.out.println(text);
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            String s = bufferRead.readLine();
            opt = new int[s.length()];
            for(int i = 0; i < opt.length; i++)
                opt[i] = s.charAt(i) - 48;

            return opt;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[])
    {
        int digits = read("Digits?");
        int positions = read("Positions?");
        NumberIndex ni = new NumberIndex(digits, positions);
        SimpleBCPlayer player = new SimpleBCPlayer(ni);
        int s = ni.size();
        int c_number = Random.nextInt(s);
        int opt[] = ni.getOption(c_number);
        boolean winner = false;
        boolean error;
        do
        {
            int u_opt[] = readNumber("Try me?");
            int u_bc[] = NumberIndex.compare(opt, u_opt);
            System.out.println((new StringBuilder()).append("Bulls (Fijas):").append(u_bc[0]).append("Cows (Picas):").append(u_bc[1]).toString());
            if(u_bc[0] == positions)
            {
                winner = true;
                System.out.println("Congratulations. You are almost as intelligent as me");
            }
            int m_opt[] = player.next();
            System.out.print("I will try:");
            int arr$[] = m_opt;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                int x = arr$[i$];
                System.out.print(x);
            }

            System.out.println();
            int bc[] = new int[2];
            bc[0] = read("Bulls (Fijas)?");
            bc[1] = read("Cows (Picas)?");
            error = !player.check(m_opt, bc);
            if(error)
                System.out.println("You loser. You can't lie to me");
            if(bc[0] == positions)
            {
                winner = true;
                System.out.println("As expected, I break your number (and your legs jajajaja)");
            }
        } while(!winner && !error);
    }
}