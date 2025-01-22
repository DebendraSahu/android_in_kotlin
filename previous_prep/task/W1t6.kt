package task

// Task 6: Create a Kotlin program that uses a list to store and print the first 10 natural numbers.
fun main() {
    val a = IntArray(10)

    for (i in a.indices) {
        a[i] = i + 1 // Store the first 10 natural numbers
    }

    for (i in a.indices) {
        println("index no: " + i + " value: " + a[i])
    }
}
