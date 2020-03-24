package com.byiryu.study.model

import com.byiryu.study.conf.AppConf.ID
import com.byiryu.study.conf.AppConf.PW
import com.byiryu.study.model.data.MovieItem
import com.byiryu.study.model.local.LocalDataSource
import com.byiryu.study.model.remote.RemoteDataSource
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class Repository constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
) {
    fun getMovieList(
        query: String
    ): Single<List<MovieItem>> {
        localDataSource.setPrevQuery(query)
        return localDataSource.getAll()
            .flatMap { movies ->
                if (movies.isEmpty()) {
                    getMovieListWithRemote(query).map {
                        it
                    }
                } else {
                    Single.just(movies).map {
                        it
                    }
                }
            }
    }

    //추가
    private fun getMovieListWithRemote(query: String): Single<List<MovieItem>> {
        return remoteDataSource.getMoveList(query)
            .map { it.items }

        /**
         * 원인은 모르겠지만 아래 로직을 타면 return 을 안함
         * saveMovies 문제
         * 임시 주석
         */
//            .flatMap { movies ->
//                localDataSource.saveMovies(movies)
//                    .andThen(Single.just(movies))
//            }
    }


    fun getPrevSearchQuery(): String {
        return localDataSource.getPrevSearchQuery()
    }

    fun isAutoLogin(): Boolean {
        return localDataSource.isAutoLogin()
    }

    fun setAutoLogin() {
        localDataSource.setAutoLogin()
    }

    fun loginCheck(id: String, pw: String): Single<Boolean> {
        return if (id == ID && pw == PW) {
            Single.just(true).delay(2, TimeUnit.SECONDS)
        } else {
            Single.just(false).delay(2, TimeUnit.SECONDS)
        }
    }


}