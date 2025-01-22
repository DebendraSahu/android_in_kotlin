package task.week1;


/**
 * **Task 7:** Create a `Person` class with attributes `name`, `age`, and methods `displayInfo()`.
 * Create instances and call methods.
 */
public class W1t7 {
    public static void main(String[] args) {
        Person person = new Person("Dev", 24);
        person.displayInfo();
    }
}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("Person : name: " + name + ", age: " + age);
    }
}
