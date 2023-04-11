var ifCount = 0
var elseCount = 0
const val cheapSeatCoast = 8
const val expSeatCoast = 10

fun main() {
    val (rows,seats) = greeting()
    val cinema = mutListCreator(rows)
    boardFiller(cinema,rows,seats)
    multipleChoise(cinema,rows,seats)
}



fun greeting(): Pair<Int,Int>{
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()
    return Pair(rows,seats)
}



fun mutListCreator(rows:Int):MutableList<MutableList<String>>{
    val outList = mutableListOf<MutableList<String>>()
    for(i in 0 until rows){
        val a = mutableListOf<String>()
        outList.add(a)
    }
    return outList
}



fun boardFiller(list: MutableList<MutableList<String>>, rows:Int, columns:Int){
    for(j in 0 until columns) {
        for (i in 0 until rows) {
            list[i].add("S ")
        }
    }
}



fun boardPrint(list:MutableList<MutableList<String>>,rows:Int,seats:Int){
    println("Cinema:")
    print("  ")
    for(i in 1 until seats+1){
        print("$i ")
    }
    println()
    for(i in 0 until rows) {
        println("${i+1} ${list[i].joinToString("")}")
    }
}


fun seatChoise(list:MutableList<MutableList<String>>,rows:Int,seats:Int){
    println("Enter a row number:")
    val r = readln().toInt()
    println("Enter a seat number in that row:")
    val s = readln().toInt()
    if(r>rows||s>seats) {
        println("Wrong input!")
        seatChoise(list, rows, seats)
    } else if(list[r-1][s-1] == "B "){
        println("That ticket has already been purchased!")
        seatChoise(list,rows,seats)
    }  else {
        if (rows * seats <= 60) {
            println("Ticket price: $$expSeatCoast")
            ifCount++
        } else if (rows * seats > 60) {
            if (r <= rows / 2) {
                println("Ticket price: $$expSeatCoast")
                ifCount++
            } else if (r > rows / 2) {
                println("Ticket price: $$cheapSeatCoast")
                elseCount++
            }
        }
        list[r - 1][s - 1] = "B "
    }
}


fun statistics(rows:Int,seats:Int){
    val allTicets = ifCount + elseCount
    val allSeats = rows*seats
    val percentage:Double = allTicets.toDouble()/allSeats.toDouble()*100
    val formatPercentage = "%.2f".format(percentage)
    val currentIncome =  (ifCount * expSeatCoast) + (elseCount * cheapSeatCoast)
    var totalIncome = 0
    if(rows*seats <= 60) {
        totalIncome = rows*seats*expSeatCoast
    } else if(rows*seats > 60){
        totalIncome = rows/2*expSeatCoast*seats + (rows - rows/2)*cheapSeatCoast*seats
    }

    println("Number of purchased tickets: $allTicets")
    println("Percentage: $formatPercentage%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")
}


fun multipleChoise(list:MutableList<MutableList<String>>,rows:Int,seats:Int){
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
    val num = readln().toInt()
    when(num){
        1 -> {
            boardPrint(list, rows, seats)
            multipleChoise(list, rows, seats)
        }
        2 -> {
            seatChoise(list, rows, seats)
            boardPrint(list, rows, seats)
            multipleChoise(list, rows, seats)
        }
        3-> {
            statistics(rows, seats)
            multipleChoise(list, rows, seats)
        }
    }
}
