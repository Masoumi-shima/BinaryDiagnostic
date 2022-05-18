import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinaryDiagnostic
{
    private static final String FILE_PATH = "/Users/melpomene/Downloads/input3.txt";

    public static void main(String[] args)
    {
        List<String> information = getDiagnosticsInformation();
        System.out.printf("Power Consumption : %s\n", getPowerConsumption(information));
        System.out.printf("Life Support Rating : %s",
                getOxygenGeneratorRating(information) * getCO2ScrubberRating(information));
    }

    private static List<String> getDiagnosticsInformation()
    {
        List<String> information = new ArrayList<>();
        try
        {
            File myObj = new File(FILE_PATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
            {
                information.add(myReader.nextLine());
            }
            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return information;
    }

    private static int getPowerConsumption(List<String> information)
    {
        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();
        for (int i = 0; i < information.get(0).length(); i++)
        {
            int numberOfZeros = 0;
            int numberOfOnes = 0;
            for (String s : information)
            {
                if (s.charAt(i) == '0')
                {
                    numberOfZeros++;
                }
                else
                {
                    numberOfOnes++;
                }
            }
            gammaRate.append(numberOfZeros > numberOfOnes ? "0" : "1");
            epsilonRate.append(numberOfZeros < numberOfOnes ? "0" : "1");
        }
        int gammaRateInt = Integer.parseInt(gammaRate.toString(), 2);
        int epsilonRateInt = Integer.parseInt(epsilonRate.toString(),2);
        return gammaRateInt * epsilonRateInt;
    }

    private static int getOxygenGeneratorRating(List<String> information)
    {
        List<String> newInformation = information.stream().toList();
        int numberOfBits = newInformation.get(0).length();
        for (int i = 0; i < numberOfBits; i++)
        {
            int numberOfZeros = 0;
            int numberOfOnes = 0;
            for (String s : newInformation)
            {
                if (s.charAt(i) == '0')
                {
                    numberOfZeros++;
                }
                else
                {
                    numberOfOnes++;
                }
            }
            char winner = numberOfOnes >= numberOfZeros ? '1' : '0';
            int finalI = i;
            newInformation = newInformation.stream().filter(data -> data.charAt(finalI) == winner).toList();
            System.out.println(finalI);
            if (newInformation.size() == 1)
            {
                break;
            }
        }
        return Integer.parseInt(newInformation.get(0), 2);
    }

    private static int getCO2ScrubberRating(List<String> information)
    {
        List<String> newInformation = information.stream().toList();
        int numberOfBits = newInformation.get(0).length();
        for (int i = 0; i < numberOfBits; i++)
        {
            int numberOfZeros = 0;
            int numberOfOnes = 0;
            for (String s : newInformation)
            {
                if (s.charAt(i) == '0')
                {
                    numberOfZeros++;
                }
                else
                {
                    numberOfOnes++;
                }
            }
            char winner = numberOfOnes >= numberOfZeros ? '0' : '1';
            int finalI = i;
            newInformation = newInformation.stream().filter(data -> data.charAt(finalI) == winner).toList();
            if (newInformation.size() == 1)
            {
                break;
            }
        }
        return Integer.parseInt(newInformation.get(0), 2);
    }
}
