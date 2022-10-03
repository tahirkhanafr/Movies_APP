package com.example.movies.data.repository

enum class status{
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState (val status: status, val msg: String) {

    companion object{
        val LOADED: NetworkState = NetworkState(status.SUCCESS,"Success")
        val LOADING: NetworkState = NetworkState(status.RUNNING,"Running")
        val  ERROR: NetworkState = NetworkState(status.FAILED,"Something went wrong!!!")
        val ENDOFLIST: NetworkState=NetworkState(status.FAILED,"You have reached the end")
    }
}