import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Programme programme = new Programme(201, "Computer Science");

        while (true) {
            clearScreen();
            System.out.println("\nChoose an action:");
            System.out.println("1. Add Tutorial Group");
            System.out.println("2. Remove Tutorial Group");
            System.out.println("3. List Tutorial Groups");
            System.out.println("4. Add Student");
            System.out.println("5. Remove Student");
            System.out.println("6. List Students in a Tutorial Group");
            System.out.println("7. Change Tutorial Group for Student");
            System.out.println("8. Merge Tutorial Groups");
            System.out.println("9. List All Students in the Programme");
            System.out.println("10. Generate Summary Report");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    clearScreen();
                    addTutorialGroup(programme, scanner);
                    break;
                case 2:
                    clearScreen();
                    removeTutorialGroup(programme, scanner);
                    break;
                case 3:
                    clearScreen();
                    listTutorialGroups(programme);
                    break;
                case 4:
                    clearScreen();
                    addStudent(programme, scanner);
                    break;
                case 5:
                    clearScreen();
                    removeStudent(programme, scanner);
                    break;
                case 6:
                    clearScreen();
                    listStudentsInTutorialGroup(programme, scanner);
                    break;
                case 7:
                    clearScreen();
                    changeTutorialGroupForStudent(programme, scanner);
                    break;
                case 8:
                    clearScreen();
                    mergeTutorialGroups(programme, scanner);
                    break;
                case 9:
                    clearScreen();
                    listAllStudentsInProgramme(programme);
                    break;
                case 10:
                    clearScreen();
                    generateSummaryReport(programme);
                    break;
                case 0:
                    clearScreen();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 10.");
            }
        }
    }

    private static void addTutorialGroup(Programme programme, Scanner scanner) {
        System.out.print("Enter Tutorial Group ID: ");
        int groupId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Tutorial Group Name: ");
        String groupName = scanner.nextLine();
        programme.addTutorialGroup(new TutorialGroup(groupId, groupName));
        System.out.println("Tutorial Group added successfully.");
    }

    private static void removeTutorialGroup(Programme programme, Scanner scanner) {
        System.out.print("Enter ID of Tutorial Group to remove: ");
        int groupId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (TutorialGroup group : programme.listTutorialGroups()) {
            if (group.getGroupId() == groupId) {
                // Assuming 'group' is a TutorialGroup object
                programme.removeTutorialGroup(group.getGroupId());
                System.out.println("Tutorial Group removed successfully.");
                return;
            }
        }
        System.out.println("Tutorial Group not found.");
    }

    private static void listTutorialGroups(Programme programme) {
        List<TutorialGroup> groups = programme.listTutorialGroups();
        System.out.println("\nList of Tutorial Groups:");
        for (TutorialGroup group : groups) {
            System.out.println("ID: " + group.getGroupId() + ", Name: " + group.getGroupName());
        }
    }

    private static void addStudent(Programme programme, Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();
        Student student = new Student(studentId, studentName);

        System.out.print("Enter Tutorial Group ID to add the student to: ");
        int groupId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        TutorialGroup targetGroup = findTutorialGroupById(programme, groupId);
        if (targetGroup != null) {
            targetGroup.addStudent(student);
            System.out.println("Student added to Tutorial Group successfully.");
        } else {
            System.out.println("Tutorial Group not found.");
        }
    }

    private static void removeStudent(Programme programme, Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        outerLoop:
        for (TutorialGroup group : programme.listTutorialGroups()) {
            for (Student student : group.listStudents()) {
                if (student.getStudentId() == studentId) {
                    group.removeStudent(student);
                    System.out.println("Student removed from Tutorial Group successfully.");
                    return;
                }
            }
        }
        System.out.println("Student not found.");
    }

    private static void listStudentsInTutorialGroup(Programme programme, Scanner scanner) {
        System.out.print("Enter Tutorial Group ID: ");
        int groupId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        TutorialGroup targetGroup = findTutorialGroupById(programme, groupId);
        if (targetGroup != null) {
            List<Student> students = targetGroup.listStudents();
            System.out.println("\nStudents in Tutorial Group " + targetGroup.getGroupName() + ":");
            for (Student student : students) {
                System.out.println("ID: " + student.getStudentId() + ", Name: " + student.getStudentName());
            }
        } else {
            System.out.println("Tutorial Group not found.");
        }
    }

    private static void changeTutorialGroupForStudent(Programme programme, Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new Tutorial Group ID: ");
        int newGroupId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        TutorialGroup newGroup = findTutorialGroupById(programme, newGroupId);
        if (newGroup == null) {
            System.out.println("New Tutorial Group not found.");
            return;
        }

        outerLoop:
        for (TutorialGroup group : programme.listTutorialGroups()) {
            for (Student student : group.listStudents()) {
                if (student.getStudentId() == studentId) {
                    group.removeStudent(student);
                    newGroup.addStudent(student);
                    System.out.println("Student's Tutorial Group changed successfully.");
                    return;
                }
            }
        }
        System.out.println("Student not found.");
    }

    private static void mergeTutorialGroups(Programme programme, Scanner scanner) {
        // Not implemented in this example
        System.out.println("Merge Tutorial Groups: Functionality not implemented.");
    }

    private static void listAllStudentsInProgramme(Programme programme) {
        List<Student> allStudents = programme.listAllStudents();
        System.out.println("\nAll students in the programme:");
        for (Student student : allStudents) {
            System.out.println("ID: " + student.getStudentId() + ", Name: " + student.getStudentName());
        }
    }

    private static TutorialGroup findTutorialGroupById(Programme programme, int groupId) {
        for (TutorialGroup group : programme.listTutorialGroups()) {
            if (group.getGroupId() == groupId) {
                return group;
            }
        }
        return null;
    }

    private static void generateSummaryReport(Programme programme) {
        System.out.println("\nSummary Report:");
        List<TutorialGroup> groups = programme.listTutorialGroups();
        for (TutorialGroup group : groups) {
            List<Student> students = group.listStudents();
            System.out.println("Tutorial Group: " + group.getGroupName() + " (ID: " + group.getGroupId() + ")");
            System.out.println("Number of Students: " + students.size());
            System.out.println("Students:");
            for (Student student : students) {
                System.out.println("ID: " + student.getStudentId() + ", Name: " + student.getStudentName());
            }
            System.out.println();
        }
    }
    
    public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    }

}