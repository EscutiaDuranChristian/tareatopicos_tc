package com.pantherhm.tareatopicos_tc

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tiptime.ui.theme.TipTimeTheme
import ui.TipCalculatorScreen

@Composable
fun App() {
    TipTimeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TipCalculatorScreen()
        }
    }
}
