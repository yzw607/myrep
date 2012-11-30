package test;

import java.util.Random;

public class CreateCard
{
    private static Random randGen = null;
    private static char[] numbersAndLetters = null;

    public static final String randomString(int length)
    {
        if (length < 1)
        {
            return null;
        }
        if (randGen == null)
        {
            randGen = new Random();
            numbersAndLetters = ("1234567890").toCharArray();
            //numbersAndLetters = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++)
        {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
            //randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
        }
        return new String(randBuffer);
    }
    
    public static void main(String[] arges)
    {
        String tmp = null;
        for(int i = 0; i < 50; i++)
        {
            tmp = randomString(8);
            System.out.println(tmp);
        }
    }

}
