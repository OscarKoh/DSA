//Programme.java
import java.util.Scanner;

class Programme {
    private int programId;
    private String programName;
    private MyArrayList<TutorialGroup> tutorialGroups;
    private static Scanner scanner = new Scanner(System.in);

    public Programme(int programId, String programName) {
        this.programId = programId;
        this.programName = programName;
        this.tutorialGroups = new MyArrayList<>();
    }

    public void addTutorialGroup(TutorialGroup tutorialGroup) {
        tutorialGroups.add(tutorialGroup);
    }

    public void removeTutorialGroup(int groupId) {
        for (int i = 0; i < tutorialGroups.size(); i++) {
            if (tutorialGroups.get(i).getGroupId() == groupId) {
                tutorialGroups.remove(i);
                return;
            }
        }
        System.out.println("Tutorial Group with ID " + groupId + " not found.");
    }

    public MyArrayList<Student> listAllStudents() {
        MyArrayList<Student> allStudents = new MyArrayList<>();
        for (int i = 0; i < tutorialGroups.size(); i++) {
            MyArrayList<Student> groupStudents = tutorialGroups.get(i).listStudents();
            for (int j = 0; j < groupStudents.size(); j++) {
                allStudents.add(groupStudents.get(j));
            }
        }
        return allStudents;
    }

    public MyArrayList<TutorialGroup> listTutorialGroups() {
        return tutorialGroups;
    }


    public void mergeTutorialGroups() {
        System.out.print("How many tutorial groups do you want to merge? ");
        int numOfGroups = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        MyArrayList<TutorialGroup> groupsToMerge = new MyArrayList<>();
        for (int i = 0; i < numOfGroups; i++) {
            System.out.print("Enter the ID of tutorial group " + (i + 1) + ": ");
            int groupId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            TutorialGroup group = findTutorialGroup(groupId);
            if (group != null) {
                groupsToMerge.add(group);
            } else {
                System.out.println("Tutorial group with ID " + groupId + " not found.");
                return;
            }
        }

        System.out.print("Enter a new ID for the merged group: ");
        int newId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter a name for the merged group: ");
        String newName = scanner.nextLine();

        TutorialGroup mergedGroup = new TutorialGroup(newId, newName);
        for (int i = 0; i < groupsToMerge.size(); i++) {
            TutorialGroup group = groupsToMerge.get(i);
            for (int j = 0; j < group.listStudents().size(); j++) {
                mergedGroup.addStudent(group.listStudents().get(j));
            }
            removeTutorialGroup(group.getGroupId());
        }
        addTutorialGroup(mergedGroup);

        System.out.println("Tutorial groups merged successfully.");
    }


    public void generateStudentsSummaryReport() {
        System.out.println("Students Summary Report:");
        for (int i = 0; i < tutorialGroups.size(); i++) {
            TutorialGroup group = tutorialGroups.get(i);
            System.out.println("Tutorial Group: " + group.getGroupName() + ", Number of Students: " + group.listStudents().size());
        }
    }

    public void generateSubjectsSummaryReport() {
        System.out.println("Subjects Summary Report:");
        for (int i = 0; i < tutorialGroups.size(); i++) {
            TutorialGroup group = tutorialGroups.get(i);
            System.out.println("Subject: " + group.getGroupName());
        }
    }

    public void changeTutorialGroupForStudent(int studentId, int newGroupId) {
        for (int i = 0; i < tutorialGroups.size(); i++) {
            TutorialGroup group = tutorialGroups.get(i);
            for (int j = 0; j < group.listStudents().size(); j++) {
                Student student = group.listStudents().get(j);
                if (student.getStudentId() == studentId) {
                    group.removeStudent(student);
                    TutorialGroup newGroup = findTutorialGroup(newGroupId);
                    if (newGroup != null) {
                        newGroup.addStudent(student);
                        System.out.println("Student's tutorial group changed successfully.");
                    } else {
                        System.out.println("New tutorial group not found.");
                    }
                    return;
                }
            }
        }
        System.out.println("Student not found in any tutorial group.");
    }

    private TutorialGroup findTutorialGroup(int groupId) {
        for (int i = 0; i < tutorialGroups.size(); i++) {
            TutorialGroup group = tutorialGroups.get(i);
            if (group.getGroupId() == groupId) {
                return group;
            }
        }
        return null;
    }
}
