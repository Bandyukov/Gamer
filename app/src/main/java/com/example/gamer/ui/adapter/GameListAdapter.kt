package com.example.gamer.ui.adapter

import com.example.gamer.core.base.BaseDiffUtilCallback
import com.example.gamer.core.base.ListItem
import com.example.gamer.core.delegate.AppDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class GameListAdapter(onItemClick: OnItemClick, onReachEndListener: OnReachEndListener) :
    AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilCallback()) {
    init {
        delegatesManager.addDelegate(AppDelegate.gameListDelegate(onItemClick, onReachEndListener))
    }
}