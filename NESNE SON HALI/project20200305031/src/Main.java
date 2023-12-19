
import java.util.*;

// 1. Inheritance

class Person {

    String name;

    String surname;

    int number;

    Person(String name, String surname, int number) {

        this.name = name;

        this.surname = surname;

        this.number = number;

    }

    private void markAttendance() {

        System.out.println("parent function to override");

    }

    void introduce() {

        System.out.println("Hello, my name is " + name + " " + surname + " (ID: " + number + ")");

    }

}

// 2. Interface



// 3. Polymorphism

class Student extends Person implements Attendee {

    Student(String name, String surname, int number) {

        super(name, surname, number);

    }

    @Override
    public void markAttendance() {

        System.out.println(name + " " + surname + " (Student ID: " + number + ") attended the class.");

    }

}

class Teacher extends Person implements Attendee {

    Teacher(String name, String surname, int number) {

        super(name, surname, number);

    }

    @Override
    public void markAttendance() {
        System.out.println(name + " " + surname + " (Teacher ID: " + number + ") marked attendance.");

    }

    void teach() {

        System.out.println(name + " " + surname + " is teaching.");

    }

}

// 4. Generic class/method

class AttendanceSystem<T extends Attendee> {

    List<T> attendees;

    AttendanceSystem() {

        this.attendees = new ArrayList<>();

    }

    void addAttendee(T attendee) {

        attendees.add(attendee);

    }

    void markAllAttendance() {

        for (T attendee : attendees) {

            attendee.markAttendance();

        }

    }

}

// 5. Generic collections

public class Main {


    public static void menuProcess() {
        Scanner scanner = new Scanner(System.in);

        // Öğrenci ve öğretmen oluşturma
        Student student1 = new Student("Will", "Smith", 123);
        Student student2 = new Student("Bill", "Gates", 007);
        Teacher teacher = new Teacher("Elon", "Musk", 010);

        // Öğrenci ve öğretmenleri bir generic koleksiyona ekleme
        AttendanceSystem<Attendee> attendanceSystem = new AttendanceSystem<>();
        attendanceSystem.addAttendee(student1);
        attendanceSystem.addAttendee(student2);
        attendanceSystem.addAttendee(teacher);

        // CLI menüsü
        while (true) {
            System.out.println("\n--- Yoklama Sistemi Menu ---");
            System.out.println("1. Tüm Katılımcıları Listele");
            System.out.println("2. Yeni Öğrenci Ekle");
            System.out.println("3. Öğrenci Bilgilerini Güncelle");
            System.out.println("4. Geç Katılım Ekle");
            System.out.println("5. Çıkış");

            System.out.print("Seçiminizi yapın: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Boş satırı oku

            switch (choice) {
                case 1:
                    // Tüm katılımcıların yoklamasını alıp raporlama
                    attendanceSystem.markAllAttendance();
                    break;
                case 2:
                    // Yeni öğrenci ekleme
                    System.out.print("Yeni öğrencinin adını girin: ");
                    String newStudentName = scanner.nextLine();
                    System.out.print("Yeni öğrencinin soyadını girin: ");
                    String newStudentSurname = scanner.nextLine();
                    System.out.print("Yeni öğrencinin numarasını girin: ");
                    int newStudentNumber = scanner.nextInt();
                    Student newStudent = new Student(newStudentName, newStudentSurname, newStudentNumber);
                    attendanceSystem.addAttendee(newStudent);
                    System.out.println(newStudentName + " " + newStudentSurname + " öğrencisi eklendi.");
                    break;
                case 3:
                    // Öğrenci bilgilerini güncelleme
                    System.out.print("Güncellenecek öğrencinin numarasını girin: ");
                    int updateStudentNumber = scanner.nextInt();
                    scanner.nextLine(); // Boş satırı oku
                    for (Attendee attendee : attendanceSystem.attendees) {
                        if (attendee instanceof Student && ((Student) attendee).number == updateStudentNumber) {
                            System.out.print("Yeni adı girin: ");
                            ((Student) attendee).name = scanner.nextLine();
                            System.out.print("Yeni soyadı girin: ");
                            ((Student) attendee).surname = scanner.nextLine();
                            System.out.println("Öğrenci bilgileri güncellendi.");
                            break;
                        }
                    }
                    break;
                case 4:
                    // Geç katılım ekleme
                    System.out.print("Geç katılım eklemek istediğiniz öğrencinin numarasını girin: ");
                    int lateStudentNumber = scanner.nextInt();
                    scanner.nextLine(); // Boş satırı oku
                    for (Attendee attendee : attendanceSystem.attendees) {
                        if (attendee instanceof Student && ((Student) attendee).number == lateStudentNumber) {
                            ((Student) attendee).markAttendance(); // Geç katılımı işaretle
                            break;
                        }
                    }
                    break;
                case 5:
                    // Çıkış
                    System.out.println("Programdan çıkılıyor...");
                    System.exit(0);
                default:
                    System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
            }
        }
    }

    public static <T> boolean genericIsEqualMethodImplementation(T obj1, T obj2) {
        return obj1.equals(obj2);
    }
    // lambda function interface



    public static void main(String[] args) {

        // lambda function implementation
        Lambda EnterPassPrint = () -> System.out.println("Please enter password");

        // lambda function implementation
        Lambda ErrorCred = () -> System.out.println("Wrong credentials!");

        Scanner in = new Scanner(System.in);

        System.out.println("***************************** signin  *****************************");
        System.out.println("Please enter userid");
        String userid = in.nextLine();

        EnterPassPrint.apply();
        String password = in.nextLine();


        // generic method implementation
        if(genericIsEqualMethodImplementation( "defaultUserid", userid) && genericIsEqualMethodImplementation("properPassword" , password ) ){
            menuProcess();
        }else{
            ErrorCred.apply();
        }

    }

}
