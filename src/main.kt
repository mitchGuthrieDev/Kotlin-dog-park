package collections

import kotlin.system.exitProcess
import java.util.Scanner
import java.io.File

fun main(args: Array<String>){
	//Initial variables
    val reader = Scanner(System.`in`)
	val dogParkRoster = arrayListOf<Dog>()
	var printedList = "Dogs at the Park: "
	
	//Creating dog objects to add to the roster
    val Puppy = Dog("Puppy",45,20,"Mitch (Author)")
	dogParkRoster.add(Puppy)
	val Puppy3 = Dog("Jax",24,20,"Mitch (Author)")
	dogParkRoster.add(Puppy3)
	val Puppy4 = Dog("Shasta",55,20,"Mitch (Author)")
	dogParkRoster.add(Puppy4)
	val Puppy5 = Dog("Sammy",2,20,"Mitch (Author)")
	dogParkRoster.add(Puppy5)
	val Puppy6 = Dog("Georgia",77,20,"Mitch (Author)")
	dogParkRoster.add(Puppy6)
	val Puppy7 = Dog("Tucker",7,20,"Mitch (Author)")
	dogParkRoster.add(Puppy7)
	val Puppy8 = Dog("Daisy",12,20,"Mitch (Author)")
	dogParkRoster.add(Puppy8)
	val Puppy9 = Dog("Robert",31,20,"Mitch (Author)")
	dogParkRoster.add(Puppy9)
	val Puppy10 = Dog("Lily",18,20,"Mitch (Author)")
	dogParkRoster.add(Puppy10)
	
	//printing info of dog1
    Puppy.getInfo()

	//Asking user for input information, creating userDog
	println("That is my dog 'Puppy', what's your dog like?")
	println("please input their name: ")
	val userDogName = readLine()!!
	println("please input their height: ")
	val userDogHeight:Int = reader.nextInt()
	println("please input their weight: ")
	val userDogWeight:Int = reader.nextInt()
	val userPuppy = Dog(userDogName, userDogHeight, userDogWeight, "User")
	
	//printing info
	userPuppy.getInfo()
	
	//Calling interface function
	println("How many times would you like to train you dog?")
	val trainTimes:Int = reader.nextInt()
	userPuppy.train(trainTimes);
	
	//Control Flow and input char | adding userDog to arrayList
	println("That's great! Would you like to take them to the dog park now? ")
	println("please input Yes or No")
	val yn = readLine()!!
	if (yn == "yes" || yn == "Yes"){
		println("Here are all the dogs at the dog park: ")
		//adding userPuppy to ArrayList
		dogParkRoster.add(userPuppy)
	} else {
		println("Ok, have a nice day")
		//Closing program
		exitProcess(-1)
	}
	
	//Printing all items in arrayList, including users dog, for loop
	for (dog in dogParkRoster){
		println(dog.name)
	}
	
	//Printing to a text file
	println("All the dogs are having fun! Do you want a copy of the roster?")
	println("please input Yes or No ('yes' will create a text file)")
	val yn2 = readLine()!!
	if (yn2 == "yes" || yn2 == "Yes"){
		println("Exporting. . .")
	} else {
		println("Ok, have a nice day")
		//Closing Program
		exitProcess(-1)
	}
	
	for (dog in dogParkRoster){
		//Making string based on a list of all dogs
		printedList = printedList + " \n" + dog.name
	}
	
	//printing string to file 
	File("Dog Park Roster.txt").bufferedWriter().use { out ->
        out.write(printedList)
	}
	
	println("Finished! You can find the 'Dog Park Roster.txt' in the project folder")
	exitProcess(-1)
	
}

//Class
open class Animal(val name: String, var height: Int, var weight: Int) {
    init {
		//using regex to find uncompatiable input, similar to tokenizer
        val regex = Regex(".*\\d+.*")
        require(!name.matches(regex)) { "You can't name an animal a number" }
        require(height > 0) { "Height must be greater than 0 inches" }
        require(weight > 0) { "Weight must be greater than 0 pounds" }
    }

    open fun getInfo(): Unit {
        println("$name is $height tall and weighs $weight")
    }
}

//Interface
interface Trainable{
    var istrain:Boolean
    fun train(times:Int): Unit
}

//Inheritance
class Dog constructor(name: String,height: Int,weight: Int, var owner: String,  var istrain: Boolean = true): Animal(name,height,weight){
        override fun getInfo(): Unit {
            println("The dog, $name is $height inches tall and weighs $weight punds; owned by: $owner")
        }
		
        //From interface
		fun train(times: Int): Unit{
        if(istrain){
            println(name+" was trained $times" + " times")
        }
    }
}

