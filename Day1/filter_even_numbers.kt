// filter even numbers
fun filterOutEvenNumbers(numbers: List<Int>): List<Int> {
    return numbers.filter {it % 2 == 0 }
}

fun main() {
    val numbers = (0..100).toList()
    val evenNumbers = filterOutEvenNumbers(numbers)
    println("numbers $numbers")
    println("even numbers $evenNumbers")
}
