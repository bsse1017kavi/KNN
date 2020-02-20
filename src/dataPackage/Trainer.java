package dataPackage;

import java.io.*;
import java.util.*;

public class Trainer
{
    public static int folds = 10;

    public static int totalData = 150;

    String filePath = "iris.data";
    String outputPath = "output/training";

    String [] values;

    public static String [] lines = new String[totalData];
    public static List<String> linesList;

    ArrayList<Iris> data;

    public static boolean [][] isTestData = new boolean[folds][totalData];

    public void initialize()
    {
        File file = new File(filePath);

        String s;

        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

            int i = 0;

            while((s=br.readLine())!=null && i<totalData)
            {
                lines[i] = s;
                i++;
            }

            linesList =  Arrays.asList(lines);


        }catch(IOException e)
        {
            e.printStackTrace();
        }

        int testData = totalData/folds;

        //Random rand = new Random();

        //int count = 0;

        for(int i=0;i<folds;i++)
        {
            /*for(int j=0;j<testData;j++)
            {
                int num;

                do
                {
                    num = rand.nextInt(totalData);
                }while(isTestData[i][num]);

                isTestData[i][num] = true;

            }*/

            Collections.shuffle(linesList);

            /*for(String s1: linesList)
            {
                System.out.println(s1);
            }*/

            for(int j=i*totalData/folds;j<totalData/folds*(i+1);j++)
            {
                isTestData[i][j] = true;
                //count++;
            }
        }

        //System.out.println(count);

    }

    public void train()
    {
        for(int z=0; z < folds;z++)
        {
            int i = 0;

            data = new ArrayList<>();

            for(String s: linesList)
            {
                if(!isTestData[z][i])
                {
                    values = s.split(",");

                    Iris iris = new Iris(Double.parseDouble(values[0]),Double.parseDouble(values[1]),
                            Double.parseDouble(values[2]),Double.parseDouble(values[3]), values[4]);

                    //System.out.println(iris);

                    data.add(iris);
                }

                i++;

            }

           try
           {
               FileOutputStream fstream = new FileOutputStream(outputPath+z+".txt");
               ObjectOutputStream ostream = new ObjectOutputStream(fstream);
               ostream.writeObject(data);
               ostream.close();
               fstream.close();

           }catch (FileNotFoundException e)
           {

           }catch (IOException e1)
           {

           }

        }
    }
}
