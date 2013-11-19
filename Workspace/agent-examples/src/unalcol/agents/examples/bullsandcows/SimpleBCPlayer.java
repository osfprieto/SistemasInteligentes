package unalcol.agents.examples.bullsandcows;

import unalcol.random.Random;
import unalcol.types.collection.vector.Vector;

// Referenced classes of package unalcol.agents.examples.bullsandcows:
//            NumberIndex

public class SimpleBCPlayer
{

    public SimpleBCPlayer(NumberIndex _ni)
    {
        options = new Vector();
        ni = _ni;
        int s = ni.size();
        for(int i = 0; i < s; i++)
            options.add(Integer.valueOf(i));

    }

    public int[] next()
    {
        return ni.getOption(((Integer)options.get(Random.nextInt(options.size()))).intValue());
    }

    public boolean check(int option[], int bc[])
    {
        for(int i = 0; i < options.size();)
        {
            int opt[] = ni.getOption(((Integer)options.get(i)).intValue());
            int bc2[] = NumberIndex.compare(option, opt);
            if(bc2[0] != bc[0] && bc2[1] != bc[1])
                options.remove(i);
            else
                i++;
        }

        return options.size() > 0;
    }

    protected NumberIndex ni;
    protected Vector options;
}