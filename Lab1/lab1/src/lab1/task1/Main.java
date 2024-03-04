package lab1.task1;
import lab1.task2.Student;
import lab1.task2.Course;



import static lab1.task2.StudentAllocator.createStudents;

public class Main {
    public static void main(String[] args) {
        Student[] x = createStudents();
        Course curs = new Course("PAO",5,x);
        for(Student elem: curs.showAllPassingStudents()){
            if(elem!=null)System.out.println(elem);
        }
        System.out.println(curs.isStudentPassing(curs.chooseStudentRandomly()));
        System.out.println(curs.isStudentPassing(1));
    }
}
