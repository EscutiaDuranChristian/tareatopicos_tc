package com.pantherhm.tareatopicos_tc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform