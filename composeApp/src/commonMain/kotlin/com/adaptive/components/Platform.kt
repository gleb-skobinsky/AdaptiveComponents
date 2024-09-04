package com.adaptive.components

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform