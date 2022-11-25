package cinema
// deprecated
var raw_cinema = """
Cinema:
  1 2 3 4 5 6 7 8
1 S S S S S S S S
2 S S S S S S S S
3 S S S S S S S S
4 S S S S S S S S
5 S S S S S S S S
6 S S S S S S S S
7 S S S S S S S S
""".trimIndent(); // we can use there Array/MutableList/Class/... but our way is more faster
var numberPurchased = 0
var `Total income`=360
var `total seats` = 0;
var cinema=mutableListOf<MutableList<String>>()
var `Current income` = 0;

fun initCinema(rows: Int, seats:Int):Unit{
    repeat(rows){
        val l = mutableListOf<String>()
        repeat(seats){
            l.add("S")
        }
        cinema.add(l)
    }
}
fun printCinema(){
    println()

    var c:Int = 1;
    println("Cinema:")
    print(" ")
    for( i in 1 /*until*/ .. cinema.first().size ){
        print(" " + i)
    }
    println()
    for(l in cinema){
     print(c.toString()+" ")
     println(l.joinToString(" "))
        c++;
    }
    println()
}
fun getNrowAndNSeats() : Pair<Int, Int>{
    print("Enter the number of rows: ")
    val nrow = readln().toInt();
    print("Enter the number of seats in each row: ")
    val nseats = readln().toInt();
    `total seats` = nrow*nseats;
    return Pair(nrow,nseats)
}
fun getPriceForRows(rows: Int, seats: Int) : Int{
    when (val totalSeats = rows * seats){
    	in 0..60 -> {
    		return totalSeats*10;
    	}
    	else -> {
    		if ( rows%2!=0 ) return (rows / 2) * seats * 10 + (rows / 2 + 1) * seats * 8
    		else return (rows / 2) * seats * 10 + (rows / 2) * seats * 8
    	}
    }
}
fun getPrice(row: Int, seat:Int) : Int{
    if(cinema[row-1][seat-1] == "B") throw Exception("That ticket has already been purchased");
    cinema[row-1][seat-1] = "B" // check if is more than element
    numberPurchased++;
    if(cinema.size * cinema.first().size in 0..60) return 10;
    else {
        when (row) {
            in 1..cinema.first().size / 2 -> return 10
            else -> return 8
        }
    }
}

fun initCinema() {
    val (nrow, nseats) = getNrowAndNSeats()
    initCinema(nrow,nseats)   
    `Total income`=getPriceForRows(nrow,nseats)
}

fun getMenu(): Int{
    println()
    var menu = """
    1. Show the seats
    2. Buy a ticket
    3. Statistics
    0. Exit""".trimIndent()
    println(menu)
    var x = readln().toInt()
    if (x in 1..3) return x
    return 0;
}
fun getPriceProcedure() : Unit{
        var wasError:Boolean = true;
        try {
            print("Enter a row number: ")
            val row=readln().toInt()
            print("Enter a seat number in that row: ")
            var seat=readln().toInt()
            val price: Int = getPrice(row, seat)
            println("\nTicket price: $$price")
            wasError=false;
            `Current income`+=price;
        } catch(e: IndexOutOfBoundsException){
          println("Wrong input!")
        } catch(e : Exception){
            println(e.toString().split(": ")[1])
        } finally {
         if (wasError) getPriceProcedure()    
        }//stackoverflow?
}

fun getStatistic() : Unit {
    val onePercent = `total seats`/100.0
    var percentage : Double = numberPurchased.toDouble();
    if (numberPurchased>0) percentage=(numberPurchased/onePercent)
    val formatPercentage = "%.2f".format(percentage)
    println("Number of purchased tickets: $numberPurchased")
    println("Percentage: $formatPercentage%")
    println("Current income: $${`Current income`}")
    println("Total income: $"+`Total income`)
}

fun main() {
    initCinema()
   // var runned:Boolean = true;
    while(true) {
        try{
        val emenu = getMenu();
        when(emenu){
            1 -> printCinema()
            2 -> getPriceProcedure()
            3 -> getStatistic()
            0 -> break;
        }
        }catch(exc:Exception){
            println( exc.toString().split(": ")[1] )
        }
        
        
        
        // write your code here
    }
}
