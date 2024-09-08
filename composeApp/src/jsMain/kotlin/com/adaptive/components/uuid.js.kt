package com.adaptive.components

external fun require(module: String): dynamic

val uuid = require("uuid")

actual fun uuid(): String = uuid.v4() as String