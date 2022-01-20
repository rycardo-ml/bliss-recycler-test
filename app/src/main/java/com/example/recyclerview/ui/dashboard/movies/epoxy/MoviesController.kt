package com.example.recyclerview.ui.dashboard.movies.epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.recyclerview.ui.dashboard.NameModel_
import com.example.recyclerview.ui.dashboard.movies.service.Movie

internal class MoviesController(
    private val onClick: (String) -> Unit
): PagedListEpoxyController<Movie>() {

    override fun buildItemModel(currentPosition: Int, item: Movie?): EpoxyModel<*> {
        return NameModel_()
            .id("name_$currentPosition")
            .nameText(item?.title ?: "not found")
            .onClickListener(onClick)
    }
}