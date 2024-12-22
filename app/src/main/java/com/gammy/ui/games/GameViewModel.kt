package com.gammy.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _games = MutableLiveData<List<Game>>(emptyList())
    val games: LiveData<List<Game>> get() = _games

    fun addGame(game: Game) {
        _games.value = _games.value.orEmpty() + game
    }
}