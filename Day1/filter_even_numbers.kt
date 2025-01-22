import kotlin.random.Random
// filter even numbers, square them, and sort the result.
fun main() {
    val numbers = List(50) { Random.nextInt(0, 200) }
    println("numbers $numbers")
    val evenNumbers = numbers.filter {it % 2 == 0 }
    println("even numbers $evenNumbers")
    val squaresOfEven = evenNumbers.map { it * it }
    println("squares of even numbers $squaresOfEven")
    val sortedSquaresOfEven = squaresOfEven.sorted() 
    println("squares of even numbers sorted in ascending order $sortedSquaresOfEven")
}
