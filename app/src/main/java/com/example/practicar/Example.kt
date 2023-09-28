package com.example.practicar

object EjemploFuncionAnonima {
    @JvmStatic
    fun main(args: Array<String>) {
        // Expresión lambda para la suma
        val suma = Calculadora { a: Double, b: Double -> a + b }
        println("Suma: " + suma.calcular(5.0, 3.0))

        // Expresión lambda para la multiplicación
        val multiplicacion = Calculadora { a: Double, b: Double -> a * b }
        println("Multiplicación: " + multiplicacion.calcular(5.0, 3.0))

    }
}

interface CalculadoraMeth {
    fun calcular(a: Double, b: Double): Double
}

internal fun interface Calculadora {
    fun calcular(a: Double, b: Double): Double
}
