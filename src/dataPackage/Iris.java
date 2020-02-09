package dataPackage;

import java.io.Serializable;

public class Iris implements Serializable
{
    double sepalLength;
    double sepalWidth;
    double petalLength;
    double petalWidth;
    String className;
    double distance;

    public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String className) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.className = className;
    }

    public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
    }

    public Iris(String className, double distance) {
        this.className = className;
        this.distance = distance;
    }

    public String getClassName() {
        return className;
    }

    public double getDistance() {
        return distance;
    }

    public void calculateDistance(Iris iris)
    {
        this.distance = Math.sqrt(Math.pow((this.sepalLength-iris.sepalLength), 2)+
                Math.pow((this.petalLength-iris.petalLength), 2)+
                Math.pow((this.sepalWidth-iris.sepalWidth), 2)+
                Math.pow((this.petalWidth-iris.petalWidth), 2));

    }

    @Override
    public String toString() {
        return "Iris{" +
                "sepalLength=" + sepalLength +
                ", sepalWidth=" + sepalWidth +
                ", petalLength=" + petalLength +
                ", petalWidth=" + petalWidth +
                ", className='" + className + '\'' +
                '}';
    }

}
