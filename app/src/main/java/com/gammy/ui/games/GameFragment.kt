package com.gammy.ui.games

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gammy.R
import com.gammy.databinding.FragmentGamesBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class GameFragment : Fragment() {

    companion object {
        const val TAG: String = "GameFragment"
        private lateinit var gameViewModel: GameViewModel
        private lateinit var gameAdapter: GameAdapter
    }

    private var _binding: FragmentGamesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        gameViewModel = ViewModelProvider(requireActivity())[GameViewModel::class.java]
        _binding = FragmentGamesBinding.inflate(inflater, container, false)

        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        val recyclerView = binding.recyclerGames
        gameAdapter = GameAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = gameAdapter

        gameViewModel.games.observe(viewLifecycleOwner) { games ->
            Log.i(TAG, "Games updated: $games")
            gameAdapter.updateGames(games)
        }

        val fabAddGame: FloatingActionButton = binding.fabAddGame
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