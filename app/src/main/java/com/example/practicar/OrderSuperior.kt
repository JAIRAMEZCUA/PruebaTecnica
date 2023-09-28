package com.example.practicar

class OrderSuperior(name: String) {
    fun acceso(edad: Int, entrarCas: ((Int) -> Boolean)) {
        entrarCas(edad)
    }

    fun calculadora(num: Int, num2: Int, entrarCas: ((Int, Int) -> Int)) {
        entrarCas(num, num2)
    }

}

fun main() {
    val usu = OrderSuperior("JAIR")
    println(usu.acceso(20, ::validar))

    var funcion = { x: Int, y: Int -> x + y }
    println(usu.calculadora(1, 2, funcion))

    println(
        calculadora(2, 3) { x, y ->
            x + y
        }
    )
}

fun calculadora(num: Int, num2: Int, opera: ((Int, Int) -> Int)) {
    opera(num, num2)
}

fun validar(edad: Int): Boolean {
    return edad > 18
}
