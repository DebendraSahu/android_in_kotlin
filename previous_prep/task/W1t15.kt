package task

/**
 * **Task 15:** Write a Kotlin program that uses a lambda expression to filter and print even numbers from a list.
 */
fun main() {
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    numbers.filter { element -> element % 2 == 0 }
           .forEach { number: Int -> println(number) }
}