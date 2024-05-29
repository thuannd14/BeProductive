package com.ndt.beproductive

interface OnMainCallBack {
    fun showFrag(tag: String, data: Any?, isBack: Boolean)
    fun backPrevious()
}