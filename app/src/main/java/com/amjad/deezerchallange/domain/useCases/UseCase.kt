package com.amjad.deezerchallange.domain.useCases

import io.reactivex.Observable

interface UseCase<T,R> {

    fun execute(parameter:T):Observable<R>
}