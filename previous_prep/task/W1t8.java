package task.week1;


/**
 * **Task 8:** Implement inheritance by creating a `Student` class that extends `Person` and adds
 * `studentId` attribute and overrides
 */
public class W1t8 {
    public static void main(String[] args) {
        Person person = new Student("Dev", 24, 21);
        person.displayInfo();
    }
}

class Student extends Person {
    int studentId;

    public Student(String name, int age, int studentId) {
        super(name, age);
        this.studentId = studentId;
    }

    @Override
    public void displayInfo() {
        System.out.println("Person : name: " + name + ", age: " + age + ", studentId: " + studentId);
    }
}
