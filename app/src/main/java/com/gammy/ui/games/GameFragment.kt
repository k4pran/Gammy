package com.gammy.ui.games

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gammy.R
import com.gammy.databinding.FragmentGamesBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class GameFragment : Fragment() {

    companion object {
        const val TAG: String = "GameFragment"
        private lateinit var gameViewModel: GameViewModel
    }

    private var _binding: FragmentGamesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gamesViewModel =
            ViewModelProvider(this).get(GameViewModel::class.java)

        _binding = FragmentGamesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // todo add games view

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fabAddGame: FloatingActionButton = requireView().findViewById(R.id.fab_add_game)
        fabAddGame.setOnClickListener { createNewGame() }
    }

    private fun createNewGame() {
        Log.i(TAG, "Navigating to GameCreationFragment")
        findNavController().navigate(R.id.gameCreationFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}