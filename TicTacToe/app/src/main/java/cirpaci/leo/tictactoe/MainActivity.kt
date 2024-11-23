package cirpaci.leo.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(),OnClickListener {
    lateinit var startGame : Button
    lateinit var userName : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startGame = findViewById(R.id.startGame)
        userName = findViewById(R.id.userName)
        startGame.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.startGame -> {
                var intention : Intent = Intent(this, Game::class.java)
                intention.putExtra("username",userName.text.toString())
                setResult(RESULT_OK,intention)
                startActivity(intention)
            }
        }
    }
}