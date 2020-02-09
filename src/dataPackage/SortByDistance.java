package dataPackage;

import java.util.Comparator;

class SortbyDistance implements Comparator<Iris>
{
    // Used for sorting in ascending order of
    // roll number
    public int compare(Iris a, Iris b)
    {
        return Double.compare(a.getDistance(),b.getDistance());
    }
}
