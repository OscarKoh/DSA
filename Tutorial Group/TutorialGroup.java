import java.util.ArrayList;
import java.util.List;

public class TutorialGroup {
    private int groupId;
    private String groupName;
    private List<Student> students;

    public TutorialGroup(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public List<Student> listStudents() {
        return students;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }
}
