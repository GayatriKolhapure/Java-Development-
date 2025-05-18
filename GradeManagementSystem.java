import java.util.Scanner;

class Student {
    
    String name;
    int rollNo;
    int[] marks = new int[5];
    double average;
    String grade;

    void inputDetails(Scanner sc){
        System.out.println("Enter Name : ");
        name = sc.nextLine();
        System.out.println("Enter Roll No. : ");
        rollNo = sc.nextInt();
        System.out.println("Enter Marks: ");

        for(int i=0; i<marks.length; i++){
            System.out.println("Subject "+(i+1)+": ");
            marks[i] = sc.nextInt();
        }
        sc.nextLine();
    }
    void calculateAvg(){
        int total = 0;
        for(int mark: marks){
            total += mark;
        }

        average = total/5.0;
    }
    void calculateGrade(){
        if(average > 85) grade = "A";
        else if(average > 70) grade = "B";
        else if(average > 55) grade = "C";
        else if(average > 35) grade = "D";
        else grade =  "Fail";
    }
    void displayResult(){
        System.out.println("-----RESULT-----");
        System.out.println("Name: "+name);
        System.out.println("Roll No. : "+rollNo);
        System.out.println("Average Marks: "+average);
        System.out.println("Grade: "+grade);
    }
    

}
public class GradeManagementSystem{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter total number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        Student[] students = new Student[n];

        for(int i=1; i<=n; i++){
            System.out.println("\n Enter details for student:  "+i);
            students[i] = new Student();
            students[i].inputDetails(sc);
            students[i].calculateAvg();
            students[i].calculateGrade();
        }

        System.out.println("\n ---All Students Result---");
        sc.nextLine();
        for(Student student : students){
            student.displayResult();
            System.out.println("--------------------------");
        }
        sc.close();

    }
}



