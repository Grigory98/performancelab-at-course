public class Line {
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    private double distance;

    public boolean IsDistanceEqual(Line line2)
    {
        var isEqual = this.distance == line2.distance;
        if(isEqual)
            System.out.println("The distance between lines is equal.");

        return isEqual;
    }

    public double GetDistance()
    {
        return this.distance;
    }

    public Line(int x1, int y1, int x2, int y2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        this.distance = Math.sqrt(Math.pow((this.x2 - this.x1), 2) + Math.pow((this.y2 - this.y1), 2));
    }
}
