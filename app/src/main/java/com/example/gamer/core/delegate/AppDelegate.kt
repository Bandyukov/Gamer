package com.example.gamer.core.delegate

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.gamer.R
import com.example.gamer.core.entities.Game
import com.example.gamer.core.base.ListItem
import com.example.gamer.databinding.GameItemBinding
import com.example.gamer.ui.adapter.OnItemClick
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object AppDelegate {

    fun gameListDelegate(onItemClick: OnItemClick) = adapterDelegateViewBinding<Game, ListItem, GameItemBinding>(
        { inflater, container ->
            GameItemBinding.inflate(inflater, container, false)
        }
    ) {
        bind {
            val resources = binding.root.resources

            binding.textViewTitle.text = item.title
            binding.textViewReleased.text = item.released
            binding.textViewRating.text = String.format(
                resources.getString(R.string.rating_value),
                item.rating,
                item.topRating
            )

            Glide.with(binding.root)
                .load(item.posterPath)
                .override(
                    resources.getDimensionPixelOffset(R.dimen.poster_width),
                    resources.getDimensionPixelOffset(R.dimen.poster_height)
                )
                .transform(
                    CenterCrop(),
                    RoundedCorners(resources.getDimensionPixelOffset(R.dimen.radius))
                )
                .transition(withCrossFade())
                .into(binding.imageViewPoster)

            binding.gameContainer.setOnClickListener {
                onItemClick.onClick(adapterPosition)
            }

            binding.executePendingBindings()
        }
    }
}