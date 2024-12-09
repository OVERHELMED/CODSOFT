import java.util.*;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int registered;

    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.registered = 0;
    }

    public boolean isAvailable() {
        return registered < capacity;
    }

    public void registerStudent() {
        if (isAvailable()) {
            registered++;
            System.out.println("Successfully registered for " + title);
        } else {
            System.out.println("Sorry, " + title + " is full.");
        }
    }

    public void removeStudent() {
        if (registered > 0) {
            registered--;
            System.out.println("Successfully dropped from " + title);
        } else {
            System.out.println("No students registered for " + title);
        }
    }
}

class Student {
    String studentID;
    String name;
    List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerForCourse(Course course) {
        if (course.isAvailable()) {
            course.registerStudent();
            registeredCourses.add(course);
        } else {
            System.out.println("Cannot register, course is full.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.removeStudent();
            registeredCourses.remove(course);
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public void viewRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered courses for " + name + ":");
            for (Course course : registeredCourses) {
                System.out.println(course.title);
            }
        }
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample Courses
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Introduction to Computer Science", "Learn the basics of programming.", 3));
        courses.add(new Course("CS102", "Data Structures", "Learn data structures like arrays, linked lists.", 2));
        courses.add(new Course("CS103", "Algorithms", "Study sorting and searching algorithms.", 2));

        // Sample Student
        Student student = new Student("S1001", "John Doe");

        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to the Course Registration System");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Courses:");
                    for (Course course : courses) {
                        System.out.println(course.courseCode + " - " + course.title + " (" + course.capacity
                                + " slots available)");
                    }
                    break;

                case 2:
                    System.out.println("Enter the course code to register: ");
                    String codeToRegister = scanner.nextLine();
                    Course courseToRegister = findCourseByCode(courses, codeToRegister);
                    if (courseToRegister != null) {
                        student.registerForCourse(courseToRegister);
                    } else {
                        System.out.println("Invalid course code.");
                    }
                    break;

                case 3:
                    System.out.println("Enter the course code to drop: ");
                    String codeToDrop = scanner.nextLine();
                    Course courseToDrop = findCourseByCode(courses, codeToDrop);
                    if (courseToDrop != null) {
                        student.dropCourse(courseToDrop);
                    } else {
                        System.out.println("Invalid course code.");
                    }
                    break;

                case 4:
                    student.viewRegisteredCourses();
                    break;

                case 5:
                    System.out.println("Thank you for using the system. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    private static Course findCourseByCode(List<Course> courses, String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
