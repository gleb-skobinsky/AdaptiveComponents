package com.adaptive.components.util

import platform.Foundation.NSUUID

actual fun uuid() = NSUUID.UUID().UUIDString()