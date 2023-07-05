package online.proyi.codeSegment.kotlin

import java.time.LocalDate

fun main() {
    var a = 1
    val s1 = "a is $a"

    println(s1)

    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)

    testLocalDate();
}

fun testLocalDate() {
    val localDate = LocalDate.now();
    println(localDate)

}
