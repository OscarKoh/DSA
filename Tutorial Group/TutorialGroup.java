// TutorialGroup.java (Entity class)
class TutorialGroup {
    private int groupId;
    private String groupName;
    private MyArrayList<Student> students;

    public TutorialGroup(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.students = new MyArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).equals(student)) {
                students.remove(i);
                return;
            }
        }
    }

    public MyArrayList<Student> listStudents() {
        return students;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }
}
