package lab2;

import static java.lang.Math.sin;

public class task6 {
    public static void main(String[] args){
        Romb x = new Romb(4,5,30.0,150.0);
        System.out.println(x.arie());
        Dreptunghi y = new Dreptunghi(4,5);
        System.out.println(y.arie());
        Patrat z = new Patrat(5);
        System.out.println(z.arie());
    }
}

class Patrulater {
    public int latura1, latura2, latura3, latura4;
    public double unghi1, unghi2, unghi3, unghi4;

    public Patrulater() {
        this(0, 0, 0, 0);
    }

    public Patrulater(int latura1, int latura2, int latura3, int latura4) {
        this.latura1 = latura1;
        this.latura2 = latura2;
        this.latura3 = latura3;
        this.latura4 = latura4;
    }

    public Patrulater(double unghi1, double unghi2, double unghi3, double unghi4) {
        this(0, 0, 0, 0, unghi1, unghi2, unghi3, unghi4);
    }

    public Patrulater(int latura1, int latura2, int latura3, int latura4,
                      double unghi1, double unghi2, double unghi3, double unghi4) {
        this(latura1, latura2, latura3, latura4);
        this.unghi1 = unghi1;
        this.unghi2 = unghi2;
        this.unghi3 = unghi3;
        this.unghi4 = unghi4;
    }

    public int perimetru() {
        int result;
        result = latura1 + latura2 + latura3 + latura4;
        return result;
    }
}

class Paralelogram extends Patrulater{
    public Paralelogram(int a, int b){
        super(a,b,a,b);
    }
    public Paralelogram(double x, double y){
        super(x,y,x,y);
    }
    public Paralelogram(int a, int b, double x, double y){
        super(a,b,a,b,x,y,x,y);
    }
    public double arie(){
        return latura1 * latura2 * sin(unghi1);
    }
}

class Romb extends Paralelogram{
    int d1,d2;
    public Romb(int d1,int d2, int lat){
        super(lat,lat);
        this.d1 = d1;
        this.d2 = d2;
    }
    public Romb(int d1, int d2, double u1, double u2){
        super(u1,u2);
        this.d1 = d1;
        this.d2 = d2;
    }
    public Romb(int d1, int d2, int lat, double u1, double u2){
        super(lat,lat,u1,u2);
        this.d1 = d1;
        this.d2 = d2;
    }
    public double arie(){
        return (double) (d1 * d2) /2;
    }
}

class Dreptunghi extends Paralelogram {
    public Dreptunghi(int x, int y) {
        super(x, y, 90, 90);
    }

    @Override
    public double arie() {
        return latura1 * latura2;
    }
}

class Patrat extends Dreptunghi{
    public Patrat(int l){
        super(l,l);
    }
    @Override
    public double arie(){
        return latura1*latura1;

    }
}