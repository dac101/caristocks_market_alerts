package com.example.marketalerts.api.retrofitmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InstrumentQuote {
    @SerializedName("message")
    @Expose
    var indices: String? = null

    @SerializedName("success")
    @Expose
    var get_50day_moving_avg: Boolean? = null

    @SerializedName("success")
    @Expose
    var get_200day_moving_avg: Boolean? = null

    @SerializedName("success")
    @Expose
    var get_current_price: Boolean? = null
}


/*
Response body
Download
{
    "indices": "AAPL",
    "data": {
    "get_50day_moving_avg": 143.55705,
    "get_200day_moving_avg": 132.13638,
    "get_stock_summary_url": "https://finance.yahoo.com/quote/AAPL",
    "get_current_price": 145.86
},
    "createdOn": "2021-08-12T06:51:58.563690",
    "errors": []
}*/
