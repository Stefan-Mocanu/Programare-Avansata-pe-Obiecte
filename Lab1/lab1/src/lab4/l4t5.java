package lab4;

import java.sql.SQLOutput;

public class l4t5 {
    public static void main(String[] argv){
        bar();
    }
    public static void bar() {
        try {
            throw new ClassCastException();
        }catch (ClassCastException x){
            System.out.println("Succes");
        }
        catch (RuntimeException e) {
            System.out.println("fail");
        }
    }
}
