package com.example.gamer.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.gamer.R
import com.example.gamer.core.base.ListItem
import com.example.gamer.core.entities.Game
import com.example.gamer.core.mapping.toGame
import com.example.gamer.core.network.api.ApiFactory
import com.example.gamer.core.network.models.Params
import com.example.gamer.databinding.FragmentMainBinding
import com.example.gamer.ui.adapter.GameListAdapter
import com.example.gamer.ui.adapter.OnItemClick
import com.example.gamer.ui.adapter.OnReachEndListener
import com.example.gamer.ui.presenters.GameListPresenter
import com.example.gamer.ui.presenters.GameListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.FieldPosition


class MainFragment : Fragment(), GameListView, OnItemClick, OnReachEndListener {

    private val adapter = GameListAdapter(this, this)
    private lateinit var presenter: GameListPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )

        presenter = GameListPresenter(this)

        binding.recyclerViewGames.adapter = adapter

        presenter.loadData()
        
        return binding.root
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    override fun showData(games: List<Game>) {
        adapter.items = games
        adapter.notifyDataSetChanged()
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_LONG).show()
        Log.i("res", throwable.message.toString())
    }

    override fun onClick(position: Int) {
        Toast.makeText(requireContext(), position.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onReachEnd(adapterPosition: Int) {
        presenter.readyToLoadMore(adapterPosition)
    }
}