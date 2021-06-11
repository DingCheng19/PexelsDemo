package com.unifa.pexelsdemo.data.model

data class SearchImageResult (
    val total_results:Int,
    val page:Int,
    val per_page:Int,
    val photos:List<PlPhoto>
)