package kotlin_classes

class StudentScore( val student: String,  val score: Double)

fun printGrades(studentScoreList: List<StudentScore>) {
    class ExamResult(val student: String, val score: Double){
        fun grade() = when {
            score > 85 -> "A"
            score > 75 -> "B"
            score > 65 -> "C"
            else -> "S"
        }
    }

    studentScoreList
        .stream()
        .map { ExamResult(it.student, it.score) }
        .forEach { println("Grade for ${it.student} is ${it.grade()}") }
}

fun main() {
    val studentScoreList =
        listOf(StudentScore("heshan", 90.0), StudentScore("dilan", 80.0), StudentScore("danu", 70.0))
    printGrades(studentScoreList)
}