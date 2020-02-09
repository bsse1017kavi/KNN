package dataPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Tester
{
    ArrayList<Iris> data;

    Iris [] distance = new Iris[Trainer.totalData-(Trainer.totalData/Trainer.folds)];

    int [] classNameCount = new int[3];

    double [] accuracy = new double[Trainer.folds];

    public double average(double [] arr)
    {
        double sum = 0;

        for(int i=0;i<arr.length;i++)
        {
            sum+=arr[i];
        }

        return sum/arr.length;
    }

    public int max(int [] arr)
    {
        int index = 0;
        int max = arr[0];

        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]> max)
            {
                max = arr[i];
                index = i;
            }
        }

        return index;
    }

    public void test(int k)
    {
        Trainer trainer = new Trainer();

        for(int z=0;z<Trainer.folds;z++)
        {
            double hit = 0;

            try
            {
                FileInputStream fin = new FileInputStream(trainer.outputPath+z+".txt");
                ObjectInputStream istream = new ObjectInputStream(fin);

                data = (ArrayList<Iris>) istream.readObject();

                for(int i=0;i<Trainer.lines.length;i++)
                {
                    if(Trainer.isTestData[z][i])
                    {
                        for(int l=0;l<3;l++)
                        {
                            classNameCount[l] = 0;
                        }

                        String [] values = Trainer.lines[i].split(",");

                        Iris iris = new Iris(Double.parseDouble(values[0]),Double.parseDouble(values[1]),
                                Double.parseDouble(values[3]),Double.parseDouble(values[3]));

                        int j = 0;

                        for(Iris iris1: data)
                        {
                            iris1.calculateDistance(iris);
                            distance[j] = iris1;
                            j++;
                        }

                        Arrays.sort(distance, new SortbyDistance());

                        for(int l=0;l<k;l++)
                        {
                            if(distance[l].getClassName().equals("Iris-setosa")) classNameCount[0]++;
                            else if(distance[l].getClassName().equals("Iris-versicolor")) classNameCount[1]++;
                            else classNameCount[2]++;

                        }

                        int index = max(classNameCount);

                        //System.out.println("index: "+index);

                        String className="";

                        if(index==0)
                        {
                            System.out.println("fold: "+z+" line: "+i+" Class by KNN: "+"Iris-setosa");
                            className = "Iris-setosa";
                        }

                        else if(index==1)
                        {
                            System.out.println("fold: "+z+" line: "+i+" Class by KNN: "+"Iris-versicolor");
                            className = "Iris-versicolor";
                        }

                        else if(index==2)
                        {
                            System.out.println("fold: "+z+" line: "+i+" Class by KNN: "+"Iris-virginica");
                            className = "Iris-virginica";
                        }


                        if(values[4].equals(className))
                        {
                            //System.out.println("Yes");
                            hit++;
                            //System.out.println(hit);
                        }


                       // System.out.println(accuracy[z]);
                        //System.out.println(hit);
                    }
                }

                accuracy[z] = hit/(double)(Trainer.totalData/Trainer.folds);

                istream.close();
                fin.close();

            }catch (FileNotFoundException e)
            {

            }catch (IOException e1)
            {
                e1.printStackTrace();
            }catch (ClassNotFoundException e2)
            {

            }

        }

        System.out.println("Accuracy: "+average(accuracy));
    }
}
