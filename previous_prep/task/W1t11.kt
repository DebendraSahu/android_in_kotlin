package task

/**
 * **Task 11:** Implement inheritance by creating a `Student` class that extends `Person` and adds
 * `studentId` attribute and overrides the `displayInfo()` method.
 */
fun main() {
    val person = StudentKt("Dev", 24, 35)
    person.displayInfo()
}

class StudentKt(name: String, age: Int, val studentId: Int) : PersonKt(name, age) {
    override fun displayInfo() {
        println("Person : name: $name, age: $age, student Id: $studentId")
    }
}
