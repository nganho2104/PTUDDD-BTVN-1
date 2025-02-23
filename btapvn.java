import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class Student {
    String firstName, lastName, birthdate, address, className;
    Map<String, Double> scores;
    String rank;

    public Student(String firstName, String lastName, String birthdate, String address, String className, Map<String, Double> scores) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.address = address;
        this.className = className;
        this.scores = scores;
        this.rank = calculateRank();
    }

    private String calculateRank() {
        double avg = scores.values().stream().mapToDouble(Double::doubleValue).average().orElse(0);
        if (avg >= 8.5) return "A";
        else if (avg >= 7) return "B";
        else if (avg >= 5.5) return "C";
        else if (avg >= 4) return "D";
        else return "<D";
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + className + ") - Birthdate: " + birthdate + ", Address: " + address + ", Rank: " + rank;
    }
}

class Class {
    String classCode;
    List<Student> students;

    public Class(String classCode, List<Student> students) {
        this.classCode = classCode;
        this.students = students;
    }

    public void displayClassInfo() {
        System.out.println("Class: " + classCode);
        Map<String, Long> rankSummary = new HashMap<>();
        students.forEach(student -> {
            System.out.println(student);
            rankSummary.put(student.rank, rankSummary.getOrDefault(student.rank, 0L) + 1);
        });
        System.out.println("Rank Summary: " + rankSummary);
    }
}

public class btapvn {
    public static void main(String[] args) {
        List<Class> classes = new ArrayList<>();
        String[] classNames = {"CNTT1", "CNTT2", "CNTT3"};
        String[] firstNames = {"Nguyen", "Tran", "Le", "Pham", "Hoang", "Dang", "Bui", "Do", "Ngo", "Duong", "Dinh", "Ly"};
        String[] lastNames = {"An", "Binh", "Chien", "Dung", "Em", "Giang", "Hieu", "Khoa", "Linh", "Minh", "Nhat", "Quang"};
        String[] subjects = {"Lập trình hướng đối tượng", "Quản lý dự án", "Học Máy", "Cơ sở dữ liệu", "Lập trình ứng dụng cho TBĐD"};
        Random rand = new Random();

        for (String className : classNames) {
            List<Student> students = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                Map<String, Double> scores = new HashMap<>();
                for (String subject : subjects) {
                    scores.put(subject, 4 + rand.nextDouble() * 6);
                }
                students.add(new Student(firstNames[i], lastNames[i], "2000-01-01", "Ha Noi", className, scores));
            }
            classes.add(new Class(className, students));
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Nhap ma lop (CNTT1, CNTT2, CNTT3) de xem thong tin: ");
            String inputClass = scanner.nextLine();

            // Tìm kiếm lớp học
            boolean found = false; // Biến kiểm tra xem có tìm thấy lớp học không
            for (Class cls : classes) {
                if (cls.classCode.equalsIgnoreCase(inputClass)) {
                    cls.displayClassInfo(); // Hiển thị thông tin lớp học
                    found = true; // Đánh dấu là đã tìm thấy
                    break; // Thoát khỏi vòng lặp khi tìm thấy lớp
                }
            }

            if (!found) { // Nếu không tìm thấy lớp học
                System.out.println("Khong tim thay lop hoc voi ma: " + inputClass);
            }

        }
    }
}
