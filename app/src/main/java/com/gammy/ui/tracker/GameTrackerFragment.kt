package com.gammy.ui.tracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gammy.databinding.FragmentGameTrackerBinding
import com.gammy.ui.games.GameAdapter
import com.gammy.ui.games.GameViewModel

class GameTrackerFragment : Fragment() {

    companion object {
        const val TAG = "GameTrackerFragment"
        private lateinit var gameViewModel: GameViewModel
        private lateinit var gameAdaptor: GameAdapter
    }

    private var _binding: FragmentGameTrackerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        gameViewModel = ViewModelProvider(requireActivity())[GameViewModel::class.java]
        _binding = FragmentGameTrackerBinding.inflate(inflater, container, false)

        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerGames
        gameAdaptor = GameAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = gameAdaptor

        gameViewModel.games.observe(viewLifecycleOwner) { games ->
            Log.i(TAG, "Games updated: $games")
            gameAdaptor.updateGames(games)
        }
    }
}