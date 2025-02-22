import java.util.*;

class Student {
    String firstName, lastName, birthdate, address, className;
    Map<String, Double> scores;

    public Student(String firstName, String lastName, String birthdate, String address, String className, Map<String, Double> scores) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.address = address;
        this.className = className;
        this.scores = scores;
    }

    public double averageScore() {
        return scores.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public String getRank() {
        double avg = averageScore();
        if (avg >= 8.5) return "A";
        else if (avg >= 7.0) return "B";
        else if (avg >= 5.5) return "C";
        else if (avg >= 4.0) return "D";
        else return "<D";
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " - Rank: " + getRank();
    }
}

class Classroom {
    String classId;
    List<Student> students = new ArrayList<>();

    public Classroom(String classId) {
        this.classId = classId;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Map<String, Long> countByRank() {
        Map<String, Long> rankCount = new HashMap<>();
        rankCount.put("A", 0L);
        rankCount.put("B", 0L);
        rankCount.put("C", 0L);
        rankCount.put("D", 0L);
        rankCount.put("<D", 0L);
        
        for (Student student : students) {
            rankCount.put(student.getRank(), rankCount.get(student.getRank()) + 1);
        }
        return rankCount;
    }

    public void showStudents() {
        students.forEach(System.out::println);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Classroom> classes = new HashMap<>();
        classes.put("CNTT1", new Classroom("CNTT1"));
        classes.put("CNTT2", new Classroom("CNTT2"));
        
        classes.get("CNTT1").addStudent(new Student("Nguyen", "An", "2004-03-15", "Ha Noi", "CNTT1", 
            Map.of("OOP", 8.0, "QLDA", 7.5, "Machine Learning", 9.0, "Database", 7.0, "Mobile App", 8.0)));
        classes.get("CNTT1").addStudent(new Student("Tran", "Binh", "2004-06-20", "Nam Dinh", "CNTT1", 
            Map.of("OOP", 6.0, "QLDA", 5.0, "Machine Learning", 7.0, "Database", 5.0, "Mobile App", 6.0)));
        classes.get("CNTT2").addStudent(new Student("Le", "Cuong", "2004-09-10", "Bac Giang", "CNTT2", 
            Map.of("OOP", 4.5, "QLDA", 8.0, "Machine Learning", 6.5 , "Database", 7.5, "Mobile App", 2.0)));
        classes.get("CNTT2").addStudent(new Student("Dang", "My", "2004-04-08", "Bac Ninh", "CNTT2", 
            Map.of("OOP", 9.0, "QLDA", 4.0, "Machine Learning", 8.0, "Database", 8.0, "Mobile App", 9.0)));
        classes.get("CNTT2").addStudent(new Student("Vu", "Lan Anh", "2004-01-19", "Ha Noi", "CNTT2", 
            Map.of("OOP", 5.0, "QLDA", 6.0, "Machine Learning", 9.5, "Database", 8.5, "Mobile App", 7.5)));
        while (true) {
            System.out.println("Danh sach lop hoc:");
            classes.keySet().forEach(System.out::println);
            System.out.print("Nhap ma lop de xem thong tin (hoac nhap 'exit' de thoat): ");
            String selectedClass = scanner.nextLine();
            
            if (selectedClass.equalsIgnoreCase("exit")) break;
            if (classes.containsKey(selectedClass)) {
                Classroom classroom = classes.get(selectedClass);
                System.out.println("\nDanh sach sinh vien cua lop " + selectedClass + ":");
                classroom.showStudents();
                
                System.out.println("\nTong ket so luong sinh vien theo rank:");
                classroom.countByRank().forEach((rank, count) -> System.out.println("Rank " + rank + ": " + count + " sinh vien"));
            } else {
                System.out.println("Ma lop khong hop le, vui long nhap lai!\n");
            }
        }
        scanner.close();
    }
}
