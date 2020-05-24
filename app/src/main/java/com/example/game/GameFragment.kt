package com.example.game

import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController

import com.example.game.databinding.ActivityMainBinding
import com.example.game.databinding.FragmentTitleBinding
import com.example.game.databinding.GameFragmentBinding
import kotlinx.android.synthetic.main.activity_main.*

class GameFragment : Fragment() {
    private var score=0
    private lateinit var binding: GameFragmentBinding
    private var count:Int =0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = DataBindingUtil.inflate<GameFragmentBinding>(
            inflater,
            R.layout.game_fragment, container, false
        )
        binding.Rock.setOnClickListener { play(1) }
        binding.paper.setOnClickListener { play(2) }
        binding.Scissors.setOnClickListener { play(3) }

        return binding.root
    }
    private fun play(num:Int) {
        /*Toast.makeText(this, "button clicked",
            Toast.LENGTH_SHORT).show()*/
        count++
        val randomInt = (1..3).random()
        val drawableResource1 = when (randomInt) {
            1 -> R.drawable.rock
            2 -> R.drawable.paper
            else -> R.drawable.scissors
        }
        val drawableResource2 = when (num) {
            1 -> R.drawable.rock
            2 -> R.drawable.paper
            else -> R.drawable.scissors
        }
        binding.p1.setImageResource(drawableResource2)
        binding.p2.setImageResource(drawableResource1)
        when (randomInt - num) {
            -1 -> score++
            -2 -> score--
            else -> score
        }
        when (num - randomInt) {
            -1 -> score--
            -2 -> score++
            else -> score
        }
        next(score,count)
        binding.mark.text = score.toString()

    }
    private fun next(num:Int,num1:Int) {
        if (num1 == 3) {
            if (num >= 0) {
                view?.findNavController()?.navigate(R.id.action_gameFragment_to_gameWonFragment)
            } else {
                view?.findNavController()?.navigate(R.id.action_gameFragment_to_gameOverFragment2)
            }
        }
    }
}
