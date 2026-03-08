package com.pantherhm.tareatopicos_tc

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "tareatopicos_tc",
    ) {
        App()
    }
}