package com.adewan.listmaker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

enum class CoreListType {
    GAMES,
    MOVIES,
    NOTES,
    PHOTOS,
    EXERCISE
}

@Entity
data class CoreList(
    @PrimaryKey val id: UUID,
    @ColumnInfo(name = "list_title") val title: String,
    @ColumnInfo(name = "list_type") val type: CoreListType,
    @ColumnInfo(name = "create_at") val createdOn: Long
)

@Entity
data class GameListEntry(
    @PrimaryKey val slug: String,
    @ColumnInfo(name = "game_name") val name: String,
    @ColumnInfo(name = "poster_url") val posterUrl: String,
    @ColumnInfo(name = "rating") val rating: Double,
    @ColumnInfo(name = "parent_list_id") val parentListId: UUID,
    @ColumnInfo(name = "game_summary") val summary: String
)