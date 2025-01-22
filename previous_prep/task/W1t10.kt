package task

/**
 * **Task 10:** Create a `Person` class in Kotlin with attributes `name`, `age`, and methods `displayInfo()`.
 * Create instances and call methods.
 */
fun main() {
    val person = PersonKt("Dev", 24)
    person.displayInfo()
}


open class PersonKt(val name: String, val age: Int) {
    open fun displayInfo() {
        println("Person : name: $name, age: $age")
    }
}
