import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Programme programme = new Programme(201, "Computer Science");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nTutorial Group Management System");
            System.out.println("1. Add Tutorial Group");
            System.out.println("2. Remove Tutorial Group");
            System.out.println("3. List All Tutorial Groups");
            System.out.println("4. Merge Tutorial Groups");
            System.out.println("5. Generate Summary Reports");
            System.out.println("6. Add Student to Tutorial Group");
            System.out.println("7. Remove Student from Tutorial Group");
            System.out.println("8. Change Tutorial Group for a Student");
            System.out.println("9. List All Students in Tutorial Group");
            System.out.println("10. List All Students in Programme");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addTutorialGroup(programme);
                    break;
                case 2:
                    removeTutorialGroup(programme);
                    break;
                case 3:
                    listAllTutorialGroups(programme);
                    break;
                case 4:
                    mergeTutorialGroups(programme);
                    break;
                case 5:
                    generateSummaryReports(programme);
                    break;
                case 6:
                    addStudentToTutorialGroup(programme);
                    break;
                case 7:
                    removeStudentFromTutorialGroup(programme);
                    break;
                case 8:
                    changeTutorialGroupForStudent(programme);
                    break;
                case 9:
                    listAllStudentsInTutorialGroup(programme);
                    break;
                case 10:
                    listAllStudentsInProgramme(programme);
                    break;
                case 11:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static int getUserChoice() {
        int choice = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Enter your choice: ");
            }
        }
        return choice;
    }

    private static void addTutorialGroup(Programme programme) {
        System.out.print("Enter tutorial group ID: ");
        int groupId = getUserInputInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter tutorial group name: ");
        String groupName = scanner.nextLine();
        TutorialGroup tutorialGroup = new TutorialGroup(groupId, groupName);
        programme.addTutorialGroup(tutorialGroup);
        System.out.println("Tutorial group added successfully.");
    }

    private static void removeTutorialGroup(Programme programme) {
        System.out.print("Enter tutorial group ID to remove: ");
        int groupId = getUserInputInt();
        programme.removeTutorialGroup(groupId);
    }

    private static void listAllTutorialGroups(Programme programme) {
        MyArrayList<TutorialGroup> groups = programme.listTutorialGroups();
        System.out.println("All Tutorial Groups:");
        for (int i = 0; i < groups.size(); i++) {
            TutorialGroup group = groups.get(i);
            System.out.println("ID: " + group.getGroupId() + ", Name: " + group.getGroupName());
        }
    }

    private static void mergeTutorialGroups(Programme programme) {
        programme.mergeTutorialGroups();
    }

    private static void generateSummaryReports(Programme programme) {
        System.out.println("Generate Summary Reports");
        System.out.println("1. Students Summary Report");
        System.out.println("2. Subjects Summary Report");
        System.out.print("Enter your choice: ");
        int reportChoice = getUserChoice();

        switch (reportChoice) {
            case 1:
                programme.generateStudentsSummaryReport();
                break;
            case 2:
                programme.generateSubjectsSummaryReport();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void addStudentToTutorialGroup(Programme programme) {
        System.out.print("Enter tutorial group ID: ");
        int groupId = getUserInputInt();
        scanner.nextLine(); // Consume newline
        TutorialGroup group = findTutorialGroup(programme, groupId);
        if (group != null) {
            System.out.print("Enter student ID: ");
            int studentId = getUserInputInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();
            Student student = new Student(studentId, studentName);
            group.addStudent(student);
            System.out.println("Student added to tutorial group successfully.");
        } else {
            System.out.println("Tutorial group not found.");
        }
    }

    private static TutorialGroup findTutorialGroup(Programme programme, int groupId) {
        MyArrayList<TutorialGroup> groups = programme.listTutorialGroups();
        for (int i = 0; i < groups.size(); i++) {
            TutorialGroup group = groups.get(i);
            if (group.getGroupId() == groupId) {
                return group;
            }
        }
        return null;
    }

    private static void removeStudentFromTutorialGroup(Programme programme) {
        System.out.print("Enter tutorial group ID: ");
        int groupId = getUserInputInt();
        scanner.nextLine(); // Consume newline
        TutorialGroup group = findTutorialGroup(programme, groupId);
        if (group != null) {
            System.out.print("Enter student ID to remove: ");
            int studentId = getUserInputInt();
            scanner.nextLine(); // Consume newline
            MyArrayList<Student> students = group.listStudents();
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                if (student.getStudentId() == studentId) {
                    group.removeStudent(student);
                    System.out.println("Student removed from tutorial group successfully.");
                    return;
                }
            }
            System.out.println("Student not found in the tutorial group.");
        } else {
            System.out.println("Tutorial group not found.");
        }
    }

    private static void changeTutorialGroupForStudent(Programme programme) {
        System.out.println("Change Tutorial Group for a Student");
        System.out.print("Enter student ID: ");
        int studentId = getUserInputInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new tutorial group ID: ");
        int newGroupId = getUserInputInt();
        scanner.nextLine(); // Consume newline
        programme.changeTutorialGroupForStudent(studentId, newGroupId);
    }

    private static void listAllStudentsInTutorialGroup(Programme programme) {
        System.out.print("Enter tutorial group ID: ");
        int groupId = getUserInputInt();
        scanner.nextLine(); // Consume newline
        TutorialGroup group = findTutorialGroup(programme, groupId);
        if (group != null) {
            MyArrayList<Student> students = group.listStudents();
            System.out.println("Students in Tutorial Group " + group.getGroupName() + ":");
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                System.out.println("ID: " + student.getStudentId() + ", Name: " + student.getStudentName());
            }
        } else {
            System.out.println("Tutorial group not found.");
        }
    }

    private static void listAllStudentsInProgramme(Programme programme) {
        MyArrayList<Student> allStudents = programme.listAllStudents();
        System.out.println("All Students in Programme:");
        for (int i = 0; i < allStudents.size(); i++) {
            Student student = allStudents.get(i);
            System.out.println("ID: " + student.getStudentId() + ", Name: " + student.getStudentName());
        }
    }

    private static int getUserInputInt() {
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
        return input;
    }
}
