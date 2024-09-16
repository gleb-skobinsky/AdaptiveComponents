package com.adaptive.components

import platform.Foundation.NSUUID

actual fun uuid() = NSUUID.UUID().UUIDString()