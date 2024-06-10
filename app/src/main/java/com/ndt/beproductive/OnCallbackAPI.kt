package com.ndt.beproductive

interface OnCallbackAPI {
    // data: Any?: la bat ki kieu du lieu nao va co the null.
    fun apiSuccess(key: String, data: Any?)
    fun apiError(key: String, code: Int, data: Any)
}