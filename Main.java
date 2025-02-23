import java.util.*;
import java.util.stream.Collectors;
class Student {
    String firstName, lastName, birthDate, address, className;
    double oop, projectManagement, machineLearning, database, appDev;

    public Student(String firstName, String lastName, String birthDate, String address, String className,
                   double oop, double projectManagement, double machineLearning, double database, double appDev) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.className = className;
        this.oop = oop;
        this.projectManagement = projectManagement;
        this.machineLearning = machineLearning;
        this.database = database;
        this.appDev = appDev;
    }

    public double getAverageScore() {
        return (oop + projectManagement + machineLearning + database + appDev) / 5;
    }

    public String getRank() {
        double avg = getAverageScore();
        if (avg >= 8.5) return "A";
        else if (avg >= 7) return "B";
        else if (avg >= 5.5) return "C";
        else if (avg >= 4) return "D";
        else return "<D";
    }

    public void display() {
        System.out.printf("%-10s %-10s %-10s %-15s %-10s %-5.2f %-5.2f %-5.2f %-5.2f %-5.2f %-3s\n",
                firstName, lastName, birthDate, address, className, oop, projectManagement, machineLearning, database, appDev, getRank());
    }
}

class Classroom {
    String classCode;
    List<Student> students = new ArrayList<>();

    public Classroom(String classCode) {
        this.classCode = classCode;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayStudents() {
        System.out.println("\nDanh sach sinh vien lop " + classCode + ":");
        System.out.printf("%-10s %-10s %-10s %-15s %-10s %-5s %-5s %-5s %-5s %-5s %-3s\n",
                "FirstName", "LastName", "Birthdate", "Address", "Class", "OOP", "PM", "ML", "DB", "App", "Rank");
        for (Student s : students) {
            s.display();
        }
        summarizeRank();
    }

    public void summarizeRank() {
        Map<String, Long> rankCount = students.stream().collect(Collectors.groupingBy(Student::getRank, Collectors.counting()));
        System.out.println("\nTong ket so sinh vien theo rank:");
        System.out.println("A: " + rankCount.getOrDefault("A", 0L));
        System.out.println("B: " + rankCount.getOrDefault("B", 0L));
        System.out.println("C: " + rankCount.getOrDefault("C", 0L));
        System.out.println("D: " + rankCount.getOrDefault("D", 0L));
        System.out.println("<D: " + rankCount.getOrDefault("<D", 0L));
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Classroom> classrooms = new HashMap<>();

        classrooms.put("CNTT1", new Classroom("CNTT1"));
        classrooms.put("CNTT2", new Classroom("CNTT2"));

        classrooms.get("CNTT1").addStudent(new Student("Nguyen", "An", "2001-05-12", "Hanoi", "CNTT1", 8.5, 9, 7.5, 8, 8));
        classrooms.get("CNTT1").addStudent(new Student("Tran", "Binh", "2002-07-21", "Hue", "CNTT1", 6, 7, 6.5, 5.5, 6));
        classrooms.get("CNTT2").addStudent(new Student("Le", "Chau", "2001-11-11", "Danang", "CNTT2", 9, 8.5, 9, 8, 8.5));
        classrooms.get("CNTT2").addStudent(new Student("Pham", "Dung", "2002-02-22", "Saigon", "CNTT2", 4.5, 5, 4, 3.5, 4));

        while (true) {
            System.out.println("\nDanh sach cac lop: CNTT1, CNTT2");
            System.out.print("Nhap ma lop de xem danh sach sinh vien (hoac nhap 'exit' de thoat): ");
            String code = sc.nextLine().trim();
            if (code.equalsIgnoreCase("exit")) break;

            Classroom classroom = classrooms.get(code);
            if (classroom != null) {
                classroom.displayStudents();
            } else {
                System.out.println("Lop khong ton tai. Vui long nhap lai!");
            }
        }
        sc.close();
    }
}
