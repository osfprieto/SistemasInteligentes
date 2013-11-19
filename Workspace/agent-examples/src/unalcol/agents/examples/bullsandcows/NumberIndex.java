package unalcol.agents.examples.bullsandcows;

import unalcol.types.collection.vector.Vector;

public class NumberIndex
{

    public NumberIndex(int _digits, int _positions)
    {
        digits = _digits;
        positions = _positions;
        options = new int[positions + 1];
        compute(digits, positions, options);
    }

    public static int compute(int digits, int positions, int options[])
    {
        if(options[positions] == 0)
            if(positions == 1)
                options[positions] = digits;
            else
                options[positions] = digits * compute(digits - 1, positions - 1, options);
        return options[positions];
    }

    public int getIndex(int option[], int n, Vector check)
    {
        if(n == option.length)
        {
            return 0;
        } else
        {
            int pos = check.findIndex(Integer.valueOf(option[n]));
            check.remove(pos);
            n++;
            return pos * options[positions - n] + getIndex(option, n, check);
        }
    }

    public int getIndex(int option[])
    {
        Vector check = new Vector();
        for(int i = 0; i < digits; i++)
            check.add(Integer.valueOf(i));

        return getIndex(option, 0, check);
    }

    public void getOption(int index, int option[], int n, Vector check)
    {
        if(++n < option.length)
        {
            int pos = index / options[positions - n];
            index %= options[positions - n];
            option[n - 1] = ((Integer)check.get(pos)).intValue();
            check.remove(pos);
            getOption(index, option, n, check);
        } else
        {
            option[n - 1] = ((Integer)check.get(index)).intValue();
        }
    }

    public int[] getOption(int index)
    {
        Vector check = new Vector();
        for(int i = 0; i < digits; i++)
            check.add(Integer.valueOf(i));

        int option[] = new int[positions];
        getOption(index, option, 0, check);
        return option;
    }

    public int size()
    {
        return options[positions];
    }

    public static int[] compare(int one[], int two[])
    {
        int bc[] = new int[2];
        for(int i = 0; i < one.length; i++)
        {
            int j;
            for(j = 0; j < two.length && one[i] != two[j]; j++);
            if(j >= two.length)
                continue;
            if(i == j)
                bc[0]++;
            else
                bc[1]++;
        }

        return bc;
    }

    protected int options[];
    protected int digits;
    protected int positions;
}