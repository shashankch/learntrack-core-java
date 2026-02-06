package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final Scanner scanner = new Scanner(System.in);

    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public Main() {
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        this.studentService = new StudentService(studentRepository);
        this.courseService = new CourseService(courseRepository);
        this.enrollmentService = new EnrollmentService(enrollmentRepository, studentService, courseService);
    }

    public static void main(String[] args) {
        System.out.println("=== " + AppConstants.APP_NAME + " ===");
        new Main().run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Choose option: ");
            try {
                switch (choice) {
                    case MenuOptions.OPTION_1 -> studentMenu();
                    case MenuOptions.OPTION_2 -> courseMenu();
                    case MenuOptions.OPTION_3 -> enrollmentMenu();
                    case MenuOptions.OPTION_0 -> {
                        running = false;
                        System.out.println("Exiting... Bye!");
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (EntityNotFoundException | InvalidInputException ex) {
                System.out.println("Error: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Unexpected error: " + ex.getMessage());
            }
        }
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("1) Student Management");
        System.out.println("2) Course Management");
        System.out.println("3) Enrollment Management");
        System.out.println("0) Exit");
    }

    // ---------------- Student Menu ----------------
    private void studentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("---- Student Management ----");
            System.out.println("1) Add student");
            System.out.println("2) View all students");
            System.out.println("3) Search student by ID");
            System.out.println("4) Deactivate student");
            System.out.println("5) Update student email");
            System.out.println("0) Back");

            int choice = readInt("Choose option: ");
            try {
                switch (choice) {
                    case MenuOptions.OPTION_1 -> addStudentFlow();
                    case MenuOptions.OPTION_2 -> listStudentsFlow();
                    case MenuOptions.OPTION_3 -> findStudentFlow();
                    case MenuOptions.OPTION_4 -> deactivateStudentFlow();
                    case MenuOptions.OPTION_5 -> updateStudentEmailFlow();
                    case MenuOptions.OPTION_0 -> back = true;
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (EntityNotFoundException | InvalidInputException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private void addStudentFlow() {
        System.out.println();
        System.out.println("Add Student");
        String firstName = readLine("First name: ");
        String lastName = readLine("Last name: ");
        String batch = readLine("Batch: ");

        String withEmail = readLine("Do you want to add email? (y/n): ");
        Student created;
        if ("y".equalsIgnoreCase(withEmail.trim())) {
            String email = readLine("Email: ");
            created = studentService.addStudent(firstName, lastName, email, batch);
        } else {
            created = studentService.addStudent(firstName, lastName, batch); // overloaded method
        }

        System.out.println("Created: " + created);
    }

    private void listStudentsFlow() {
        List<Student> students = studentService.listStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void findStudentFlow() {
        int id = readInt("Enter student ID: ");
        Student s = studentService.findStudentById(id);
        System.out.println(s);
    }

    private void deactivateStudentFlow() {
        int id = readInt("Enter student ID to deactivate: ");
        studentService.deactivateStudent(id);
        System.out.println("Student deactivated.");
    }

    private void updateStudentEmailFlow() {
        int id = readInt("Enter student ID: ");
        String email = readLine("New email: ");
        studentService.updateStudentEmail(id, email);
        System.out.println("Email updated.");
    }

    // ---------------- Course Menu ----------------
    private void courseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("---- Course Management ----");
            System.out.println("1) Add course");
            System.out.println("2) View all courses");
            System.out.println("3) Activate/Deactivate course");
            System.out.println("0) Back");

            int choice = readInt("Choose option: ");
            try {
                switch (choice) {
                    case MenuOptions.OPTION_1 -> addCourseFlow();
                    case MenuOptions.OPTION_2 -> listCoursesFlow();
                    case MenuOptions.OPTION_3 -> toggleCourseFlow();
                    case MenuOptions.OPTION_0 -> back = true;
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (EntityNotFoundException | InvalidInputException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private void addCourseFlow() {
        System.out.println();
        System.out.println("Add Course");
        String name = readLine("Course name: ");
        String description = readLine("Description: ");
        int weeks = readInt("Duration in weeks: ");

        Course c = courseService.addCourse(name, description, weeks);
        System.out.println("Created: " + c);
    }

    private void listCoursesFlow() {
        List<Course> courses = courseService.listCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        for (Course c : courses) {
            System.out.println(c);
        }
    }

    private void toggleCourseFlow() {
        int id = readInt("Enter course ID: ");
        Course c = courseService.findCourseById(id);

        System.out.println("Current active: " + c.getStatus().isActive());
        String yn = readLine("Set active? (y/n): ");
        CourseStatus status = "y".equalsIgnoreCase(yn.trim())? CourseStatus.ACTIVE : CourseStatus.INACTIVE;
        courseService.setCourseActive(id, status);
        System.out.println("Updated: " + courseService.findCourseById(id));
    }

    // ---------------- Enrollment Menu ----------------
    private void enrollmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("---- Enrollment Management ----");
            System.out.println("1) Enroll student in course");
            System.out.println("2) View enrollments for a student");
            System.out.println("3) Mark enrollment as COMPLETED");
            System.out.println("4) Mark enrollment as CANCELLED");
            System.out.println("0) Back");

            int choice = readInt("Choose option: ");
            try {
                switch (choice) {
                    case MenuOptions.OPTION_1 -> enrollFlow();
                    case MenuOptions.OPTION_2 -> listEnrollmentsForStudentFlow();
                    case MenuOptions.OPTION_3 -> updateEnrollmentStatusFlow(EnrollmentStatus.COMPLETED);
                    case MenuOptions.OPTION_4 -> updateEnrollmentStatusFlow(EnrollmentStatus.CANCELLED);
                    case MenuOptions.OPTION_0 -> back = true;
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (EntityNotFoundException | InvalidInputException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private void enrollFlow() {
        int studentId = readInt("Student ID: ");
        int courseId = readInt("Course ID: ");
        String date = readLine("Enrollment date (" + AppConstants.DATE_FORMAT_HINT + "): ");

        Enrollment e = enrollmentService.enrollStudent(studentId, courseId, date);
        System.out.println("Enrolled: " + e);
    }

    private void listEnrollmentsForStudentFlow() {
        int studentId = readInt("Student ID: ");
        List<Enrollment> enrollments = enrollmentService.listEnrollmentsForStudent(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for student " + studentId);
            return;
        }
        for (Enrollment e : enrollments) {
            System.out.println(e);
        }
    }

    private void updateEnrollmentStatusFlow(EnrollmentStatus status) {
        int enrollmentId = readInt("Enrollment ID: ");
        enrollmentService.updateEnrollmentStatus(enrollmentId, status);
        System.out.println("Updated: " + enrollmentService.findEnrollmentById(enrollmentId));
    }

    // ---------------- Input Helpers ----------------
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}