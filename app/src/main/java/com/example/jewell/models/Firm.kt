package com.example.jewell.models

import java.io.Serializable

data class Firm(
    var article: Int,
    var firmID: Int,
    var firmName: String):Serializable
//TODO(andreew) probably add list of created products if it's needed.