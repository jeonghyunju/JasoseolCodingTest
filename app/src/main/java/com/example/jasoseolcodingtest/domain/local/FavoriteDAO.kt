package com.example.jasoseolcodingtest.domain.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jasoseolcodingtest.model.MovieItem

@Dao
interface FavoriteDAO {
    @Query("SELECT * FROM favorite ORDER BY idx DESC")
    fun getFavorite(): LiveData<List<MovieItem>>

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE title=:title)")
    fun hasDuplicate(title: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieItem)

    @Delete
    suspend fun delete(movie: MovieItem)

    @Query("DELETE FROM favorite WHERE title=:title")
    suspend fun deleteDuplicate(title: String)

    @Transaction
    open suspend fun insertFavorite(movie: MovieItem) {
        if(hasDuplicate(movie.title)) {
            deleteDuplicate(movie.title)
            insert(movie)
        }else {
            insert(movie)
        }
    }
}