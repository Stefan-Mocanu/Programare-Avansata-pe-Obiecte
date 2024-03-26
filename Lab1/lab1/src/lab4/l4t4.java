package lab4;

public class l4t4 {
    public static void main(String[] argv){
        foo();
    }
    public static void foo() {
        try {
            System.out.println("1");
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println("2");
        }

        System.out.println("3");
    }

}
