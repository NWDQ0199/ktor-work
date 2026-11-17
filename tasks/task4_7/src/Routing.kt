// Task 4.7

import io.ktor.server.application.*
import io.ktor.server.pebble.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            //println("balls")
            call.respondTemplate("form.peb", emptyMap())
        }

        // Add route to handle POST requests containing form data
        post("/roll") {
            //println("post")
            val params=call.receiveParameters()
            val numDice=params["num_dice"]?.toInt() ?: 1
            val dieSides=params["die_sides"]?.toInt() ?: 6
            val roll=DiceRoll(numDice,dieSides)
            call.respondTemplate("result.peb",mapOf("roll" to roll,"reroll_uri" to "/roll"))
        }

        // Add route to handle GET requests for URLs like /roll/3d8
        get("/roll/{dice}") {
        //get(Regex("""(?<num>\d+)d(?<sides>\d+)""")) {
            //println(call.parameters)
            val diceStr=call.parameters["dice"]
            var numStr:String?=null
            var sideStr:String?=null
            if(diceStr?.contains("d")?:false)
            {
                val spl=diceStr.split("d")
                numStr=spl[0]
                sideStr=spl[1]
            }
            val num=numStr?.toInt()?:1
            val sides=sideStr?.toInt()?:6
            val roll=DiceRoll(num,sides)
            //println("num: $num\nsides: $sides")
            call.respondTemplate("result.peb",mapOf("roll" to roll,"reroll_uri" to "/roll/${num}d${sides}"))
        }
    }
}
