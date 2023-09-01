package com.example.notificaciones.sealed


data class Game(val id: Int, val title: String, var errorDate: ErrorDate)
sealed class ErrorDate() {
    object RayadoError : ErrorDate()
    object InternetError : ErrorDate()
    object SinError : ErrorDate()
    data class versionError(val error: String) : ErrorDate()

}

sealed interface DellValue
sealed interface Player
sealed interface Winner
object x : DellValue, Player, Winner
object y : DellValue, Player, Winner
object valueCell : DellValue

fun testFun(dell: DellValue, value: Player) {

    //dell extiende de  los objetos valuecell , x, y
    when (dell) {
        valueCell -> TODO()
        x -> TODO()
        y -> TODO()
    }

    when (value) { //extiende de x,y
        x -> TODO()
        y -> TODO()
    }
}