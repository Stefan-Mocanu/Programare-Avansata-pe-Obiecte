package lab4.l4t3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Student implements Cloneable{
    private String fullName;

    // Acest dicționar conține informații cu privire la notele
    // pe care un student le are la un curs. Intrările sunt de tip
    // numeCurs -> notăCurs
    private Map<String, Double> courseInformation;

    public Object clone(){
        Student copie = new Student();
        copie.fullName = fullName;
        copie.courseInformation = new HashMap<>();
        copie.courseInformation.putAll(courseInformation);
        return copie;
    }

    @Override
    public String toString() {
        String ret = fullName + "[";
        for(Map.Entry<String, Double> camp: courseInformation.entrySet()){
            ret += "("+camp.getKey() + " => " + camp.getValue().toString() + ")";

        }
        return ret + "]";
    }
}
