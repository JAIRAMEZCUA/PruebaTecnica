package com.example.practicar

class OuterClass {
    private val outerProperty = "Outer property"

    fun suma(num: Int): Int = num + 2


    // Inner class definition
    inner class InnerClass {
        var numero = 10
        fun printOuterProperty() {
            print(suma(3))
            println("Accessing outer property: $outerProperty")
        }
    }

    class classAnidada {
        fun printOuterProperty() {
//            No se puede acceder
//            println("Accessing outer property: $outerProperty")
        }
    }
}

fun main() {
    val outer = OuterClass()
    val inner = outer.InnerClass() // Creating an instance of the inner class

    inner.printOuterProperty() // Accessing the outer property through the inner class
}
