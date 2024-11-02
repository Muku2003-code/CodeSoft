import java.util.ArrayList;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolled;

    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0; // Initially, no students are enrolled
    }

    public boolean isFull() {
        return enrolled >= capacity;
    }

    public void enroll() {
        if (!isFull()) {
            enrolled++;
        }
    }

    public void drop() {
        if (enrolled > 0) {
            enrolled--;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + ", Title: " + title + ", Description: " + description +
                ", Capacity: " + capacity + ", Enrolled: " + enrolled;
    }
}

class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course) && !course.isFull()) {
            registeredCourses.add(course);
            course.enroll();
            System.out.println("Successfully registered for " + course.title);
        } else if (course.isFull()) {
            System.out.println("Course " + course.title + " is full.");
        } else {
            System.out.println("You are already registered for " + course.title);
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.drop();
            System.out.println("Successfully dropped " + course.title);
        } else {
            System.out.println("You are not registered for " + course.title);
        }
    }

    public void listRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course);
            }
        }
    }
}

public class StudentCourseRegistrationSystem {
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCourses();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Student Course Registration System ---");
            System.out.println("1. Register Student");
            System.out.println("2. List Courses");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. List Registered Courses");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    listCourses();
                    break;
                case 3:
                    registerForCourse();
                    break;
                case 4:
                    dropCourse();
                    break;
                case 5:
                    listRegisteredCourses();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Exiting the system. Goodbye!");
    }

    private static void initializeCourses() {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basics of programming.", 3));
        courses.add(new Course("CS102", "Data Structures", "Learn about various data structures.", 2));
        courses.add(new Course("CS103", "Algorithms", "Introduction to algorithms.", 2));
        courses.add(new Course("CS104", "Database Systems", "Learn about database management.", 3));
    }

    private static void registerStudent() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        students.add(new Student(studentID, name));
        System.out.println("Student registered successfully!");
    }

    private static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void registerForCourse() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudent(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Available Courses:");
        listCourses(); // Display available courses

        System.out.print("Enter Course Code to register: ");
        String courseCode = scanner.nextLine();
        Course course = findCourse(courseCode);
        if (course != null) {
            student.registerCourse(course);
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void dropCourse() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudent(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Registered Courses:");
        student.listRegisteredCourses(); // Display registered courses

        System.out.print("Enter Course Code to drop: ");
        String courseCode = scanner.nextLine();
        Course course = findCourse(courseCode);
        if (course != null) {
            student.dropCourse(course);
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void listRegisteredCourses() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudent(studentID);
        if (student != null) {
            student.listRegisteredCourses();
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Student findStudent(String studentID) {
        for (Student student : students) {
            if (student.studentID.equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}