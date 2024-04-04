import java.util.ArrayList;
import java.util.List;

public class Programme {
    private int programId;
    private String programName;
    private List<TutorialGroup> tutorialGroups;

    public Programme(int programId, String programName) {
        this.programId = programId;
        this.programName = programName;
        this.tutorialGroups = new ArrayList<>();
    }

    public void addTutorialGroup(TutorialGroup tutorialGroup) {
        tutorialGroups.add(tutorialGroup);
    }

    public void removeTutorialGroup(int groupId) {
        TutorialGroup groupToRemove = findTutorialGroupById(groupId);
        if (groupToRemove != null) {
            tutorialGroups.remove(groupToRemove);
            System.out.println("Tutorial Group removed successfully.");
        } else {
            System.out.println("Tutorial Group with ID " + groupId + " not found.");
        }
    }

    public List<Student> listAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        for (TutorialGroup group : tutorialGroups) {
            allStudents.addAll(group.listStudents());
        }
        return allStudents;
    }

    public List<TutorialGroup> listTutorialGroups() {
        return tutorialGroups;
    }

    private TutorialGroup findTutorialGroupById(int groupId) {
        for (TutorialGroup group : tutorialGroups) {
            if (group.getGroupId() == groupId) {
                return group;
            }
        }
        return null;
    }
}
