package cirpaci.leo.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class Game : AppCompatActivity(), OnClickListener {
    lateinit var restart : Button
    lateinit var whoPlay : TextView
    lateinit var case : Array<ImageButton>
    lateinit var data : HashMap<Int,Char>
    lateinit var playerName : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        restart = findViewById(R.id.restart)
        whoPlay = findViewById(R.id.whoPlay)
        playerName = findViewById(R.id.playerName)
        playerName.setText(intent.extras?.getString("username"))
        case = arrayOf(
            findViewById(R.id.CASE1),
            findViewById(R.id.CASE2),
            findViewById(R.id.CASE3),
            findViewById(R.id.CASE4),
            findViewById(R.id.CASE5),
            findViewById(R.id.CASE6),
            findViewById(R.id.CASE7),
            findViewById(R.id.CASE8),
            findViewById(R.id.CASE9)
        )
        data = HashMap()
        for (i in 0..8){
            data.set(i,'$')
            case.get(i).setOnClickListener(this)
        }
        restart.setOnClickListener(this)
        whoPlay.setText(R.string.PlayerTitle)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.CASE1 -> PlayerTurn(0,R.id.CASE1)
            R.id.CASE2 -> PlayerTurn(1,R.id.CASE2)
            R.id.CASE3 -> PlayerTurn(2,R.id.CASE3)
            R.id.CASE4 -> PlayerTurn(3,R.id.CASE4)
            R.id.CASE5 -> PlayerTurn(4,R.id.CASE5)
            R.id.CASE6 -> PlayerTurn(5,R.id.CASE6)
            R.id.CASE7 -> PlayerTurn(6,R.id.CASE7)
            R.id.CASE8 -> PlayerTurn(7,R.id.CASE8)
            R.id.CASE9 -> PlayerTurn(8,R.id.CASE9)
            R.id.restart -> {
                var intention : Intent = Intent(this, MainActivity::class.java)
                setResult(RESULT_OK,intention)
                startActivity(intention)
            }
        }
    }
    private fun switchStatement(EnableStatement : Boolean) {
        for(i in 0..8) {
            val button = (case.get(i))
            button.isEnabled = EnableStatement
        }
    }
    private fun winner(isComputer: Boolean): Boolean {
        var char = 'o'
        var haveWinner : Boolean = false
        if (isComputer) {
            char = 'x'
        }
        for (i in 0 until 9 step 3) {
            if (data[i] == char && data[i + 1] == char && data[i + 2] == char) haveWinner = true
        }
        for (i in 0..2) {
            if (data[i] == char && data[i + 3] == char && data[i + 6] == char) haveWinner = true
        }
        if (data[0] == char && data[4] == char && data[8] == char) haveWinner = true
        if (data[2] == char && data[4] == char && data[6] == char) haveWinner = true
        if (haveWinner) {
            var whoWin = if (isComputer == false) R.string.PlayerWiner else R.string.ComputerWiner
            whoPlay.setText(whoWin)
            val text = whoWin
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(this, text, duration)
            toast.show()
            Handler(Looper.getMainLooper()).postDelayed({
                var intention : Intent = Intent(this, Game::class.java)
                intention.putExtra("username",intent.extras?.getString("username"))
                setResult(RESULT_OK,intention)

                startActivity(intention)
            }, 2000)
        }
        return haveWinner
    }
    private fun search(): Int {
        for (i in 0 until 9 step 3) {
            if (data[i] == 'x' && data[i + 1] == 'x' && data[i + 2] == '$') return i + 2
            if (data[i] == 'x' && data[i + 1] == '$' && data[i + 2] == 'x') return i + 1
            if (data[i] == '$' && data[i + 1] == 'x' && data[i + 2] == 'x') return i
        }
        for (i in 0..2) {
            if (data[i] == 'x' && data[i + 3] == 'x' && data[i + 6] == '$') return i + 6
            if (data[i] == 'x' && data[i + 3] == '$' && data[i + 6] == 'x') return i + 3
            if (data[i] == '$' && data[i + 3] == 'x' && data[i + 6] == 'x') return i
        }
        if (data[0] == 'x' && data[4] == 'x' && data[8] == '$') return 8
        if (data[0] == 'x' && data[4] == '$' && data[8] == 'x') return 4
        if (data[0] == '$' && data[4] == 'x' && data[8] == 'x') return 0
        if (data[2] == 'x' && data[4] == 'x' && data[6] == '$') return 6
        if (data[2] == 'x' && data[4] == '$' && data[6] == 'x') return 4
        if (data[2] == '$' && data[4] == 'x' && data[6] == 'x') return 2
        for (i in 0 until 9 step 3) {
            if (data[i] == 'o' && data[i + 1] == 'o' && data[i + 2] == '$') return i + 2
            if (data[i] == 'o' && data[i + 1] == '$' && data[i + 2] == 'o') return i + 1
            if (data[i] == '$' && data[i + 1] == 'o' && data[i + 2] == 'o') return i
        }
        for (i in 0..2) {
            if (data[i] == 'o' && data[i + 3] == 'o' && data[i + 6] == '$') return i + 6
            if (data[i] == 'o' && data[i + 3] == '$' && data[i + 6] == 'o') return i + 3
            if (data[i] == '$' && data[i + 3] == 'o' && data[i + 6] == 'o') return i
        }
        if (data[0] == 'o' && data[4] == 'o' && data[8] == '$') return 8
        if (data[0] == 'o' && data[4] == '$' && data[8] == 'o') return 4
        if (data[0] == '$' && data[4] == 'o' && data[8] == 'o') return 0
        if (data[2] == 'o' && data[4] == 'o' && data[6] == '$') return 6
        if (data[2] == 'o' && data[4] == '$' && data[6] == 'o') return 4
        if (data[2] == '$' && data[4] == 'o' && data[6] == 'o') return 2
        val availablePositions = data.filterValues { it == '$' }.keys.toList()
        return availablePositions.random()
    }
    private fun isStuck() : Boolean {
        var stuck = true
        for (i in 0..8) {
            if (data[i] == '$') {
                stuck = false
                break ;
            }
        }
        if (stuck == true) {
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(this, R.string.stuck, duration)
            toast.show()
            var intention : Intent = Intent(this, Game::class.java)
            intention.putExtra("username",intent.extras?.getString("username"))
            setResult(RESULT_OK,intention)
            startActivity(intention)
        }
        return stuck
    }
    private fun PlayerTurn(position: Int, id: Int) {
        if (isStuck()) { return }
        val button = findViewById<ImageButton>(id)
        button.setImageResource(R.drawable.o)
        button.isClickable = false
        switchStatement(false)
        data.set(position, 'o')
        if (winner(false)) { return }
        whoPlay.setText(R.string.ComputerTitle)
        if (isStuck()) { return }
        Handler(Looper.getMainLooper()).postDelayed({
            ComputerTurn()
        }, 2000)
    }
    private fun ComputerTurn() {
        var id = search()
        val button = case.get(id)
        button.setImageResource(R.drawable.x)
        button.isClickable = false
        data.set(id, 'x')
        if (winner(true)) { return } else {
            switchStatement(true)
            whoPlay.setText(R.string.PlayerTitle)
        }
    }
}