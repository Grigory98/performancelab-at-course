public class Main {
    public static void main(String[] args) {
        var line1 = new Line(1, 1,2,2);
        var line2 = new Line(-3,0,1,1);

        System.out.println(line1.GetDistance());
        System.out.println(line2.GetDistance());
        System.out.println(line1.IsDistanceEqual(line2));
    }
}