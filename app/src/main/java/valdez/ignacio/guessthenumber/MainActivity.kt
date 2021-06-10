package valdez.ignacio.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView;
import android.widget.Button;
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var maxNumber:Int = 100;
    var minNumber:Int = 0;
    var number:Int = 0;
    var won:Boolean = false;
    var playing:Boolean = false;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessings:TextView = findViewById(R.id.guessings);
        val button_up:Button = findViewById(R.id.button_up);
        val button_down:Button = findViewById(R.id.button_down);
        val button_generate:Button = findViewById(R.id.button_generate);
        val button_guess:Button = findViewById(R.id.button_guess);

        button_generate.setOnClickListener{
            number = Random.nextInt(minNumber, maxNumber)
            guessings.setText(number.toString())
            button_generate.visibility = View.INVISIBLE
            button_guess.visibility = View.VISIBLE
            playing = true
        }

        button_up.setOnClickListener{
            if(playing) {
                minNumber = number
                if (checkingLimits()) {
                    number = Random.nextInt(minNumber, maxNumber)
                    guessings.setText(number.toString())
                } else {
                    guessings.setText("lmao you won")
                    playing = false
                }
            }
        }

        button_down.setOnClickListener{
            if(playing) {
                maxNumber = number
                if (checkingLimits()) {
                    number = Random.nextInt(minNumber, maxNumber)
                    guessings.setText(number.toString())
                } else {
                    guessings.setText("lmao you won")
                    playing = false
                }
            }
        }

        button_guess.setOnClickListener{
            if(!won){
                guessings.setText("lol i guessed your number, you loser. it is $number")
                button_guess.setText("PLAY AGAIN?")
                won = true
                playing = false
            }else{
                button_generate.visibility = View.VISIBLE
                guessings.setText("Tap on GENERATE to start!")
                button_guess.visibility = View.GONE
                won = false
            }
        }

    }

    fun checkingLimits():Boolean{
        return minNumber!=maxNumber
    }

}