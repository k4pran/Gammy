package com.gammy.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gammy.R

class GameCreationFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_game_creation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameViewModel = ViewModelProvider(requireActivity())[GameViewModel::class.java]

        // Get references to the input fields and button
        val nameInput = view.findViewById<EditText>(R.id.input_game_name)
        val minPlayersInput = view.findViewById<EditText>(R.id.input_min_players)
        val maxPlayersInput = view.findViewById<EditText>(R.id.input_max_players)
        val saveButton = view.findViewById<Button>(R.id.button_save_game)

        // Handle the Save button click
        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            val minPlayers = minPlayersInput.text.toString().toIntOrNull()
            val maxPlayers = maxPlayersInput.text.toString().toIntOrNull()

            if (validateInput(name, minPlayers, maxPlayers)) {
                val newGame = Game(name, minPlayers!!, maxPlayers!!)
                gameViewModel.addGame(newGame)

                // Show a success message
                Toast.makeText(requireContext(), "Game added: $name", Toast.LENGTH_SHORT).show()

                // Navigate back to GameFragment
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Invalid input. Please check your entries.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInput(name: String, minPlayers: Int?, maxPlayers: Int?): Boolean {
        return name.isNotBlank() &&
                minPlayers != null && maxPlayers != null &&
                minPlayers > 0 && maxPlayers >= minPlayers
    }
}
