package lab1.task2;
import java.util.Random;
public class Course {
    private String name;
    private double minimumGrade;
    private Student[] students;

    public Course(String name, double minimumGrade, Student[] students) {
        this.name = name;
        this.minimumGrade = minimumGrade;
        this.students = students;
    }

    public Student chooseStudentRandomly(){
        Random rand = new Random();
        return students[rand.nextInt(students.length)];
    }
    public Student[] showAllPassingStudents(){
        Student[] res = new Student[students.length];
        int i = 0;
        for (Student elem: students){
            if(elem.getGrade()>=minimumGrade){
                res[i]=elem;
                i++;
            }
        }
        return res;
    }
    public boolean isStudentPassing(Student x){
        boolean res=false;
        for (Student elem: students){
            if(elem.equals(x) && x.getGrade()>=minimumGrade){
                res=true;
                break;
            }
        }
        return res;
    }
    public boolean isStudentPassing(int x){
        return students[x].getGrade()>=minimumGrade;
    }
}
