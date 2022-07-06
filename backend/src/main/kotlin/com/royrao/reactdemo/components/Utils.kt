package com.royrao.reactdemo.components

inline fun <reified R> notNull(vararg args: Any?, block: () -> R) =
    if (args.filterNotNull().size == args.size) block() else null
