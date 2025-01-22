package task

/**
 * **Task 12:** Implement polymorphism by creating a `Shape` class with a `draw()` method,
 * and then subclasses `Circle` and `Rectangle` that override the `draw()` method.
 */
fun main() {
    val shape1: ShapeKt = CircleKt()
    val shape2: ShapeKt = RectangleKt()
    shape1.draw()
    shape2.draw()
}

internal interface ShapeKt {
    fun draw()
}

internal class CircleKt : ShapeKt {
    override fun draw() {
        println("Drawing CircleKt shape")
    }
}

internal class RectangleKt : ShapeKt {
    override fun draw() {
        println("Drawing RectangleKt shape")
    }
}
