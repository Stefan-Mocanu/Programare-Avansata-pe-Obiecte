package lab7;

public class tuple {
    Integer x, y;

    tuple(Integer x1, Integer y1) {
        x = x1;
        y = y1;
    }

    @Override
    public String toString() {
        return x.toString() + " " + y.toString();
    }
}
