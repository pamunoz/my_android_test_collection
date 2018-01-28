package com.pfariasmunoz.daggerandroidwithmvvm.common.viewmodel

class Response(val status: Status, data : String?, error : Throwable?) {
    companion object {
        fun loading() : Response = Response(Status.LOADING, null, null)
        fun success(data: String?) : Response = Response(Status.SUCCESS, data, null)
        fun error(error: Throwable?) : Response = Response(Status.ERROR, null, error)
    }
}
