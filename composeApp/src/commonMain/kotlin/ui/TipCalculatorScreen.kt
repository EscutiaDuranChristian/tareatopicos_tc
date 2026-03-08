package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Percent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import viewModel.TipCalculatorViewModel

@Composable
fun TipCalculatorScreen(
    tipCalculatorViewModel: TipCalculatorViewModel = tipCalculatorViewModel()
) {
    val uiState by tipCalculatorViewModel.uiState.collectAsState()

    DisposableEffect(Unit) {
        println("TipCalculatorScreen entramos a composicion")
        onDispose {
            println("TipCalculatorScreen dejamos composicion")
        }
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Calculate Tip",
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(
            label = "Bill Amount",
            leadingIcon = Icons.Outlined.AttachMoney,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = uiState.billAmount,
            onValueChanged = tipCalculatorViewModel::onBillAmountChange,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        EditNumberField(
            label = "Tip Percentage",
            leadingIcon = Icons.Outlined.Percent,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            value = uiState.tipPercentage,
            onValueChanged = tipCalculatorViewModel::onTipPercentageChange,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        RoundTheTipRow(
            roundUp = uiState.roundUp,
            onRoundUpChanged = tipCalculatorViewModel::onRoundUpChange,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Text(
            text = "Tip Amount: ${uiState.tipResult}",
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Composable
fun tipCalculatorViewModel(): TipCalculatorViewModel = remember { TipCalculatorViewModel() }

@Composable
private fun EditNumberField(
    label: String,
    leadingIcon: ImageVector,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        singleLine = true,
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(label) },
        keyboardOptions = keyboardOptions
    )
}

@Composable
private fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Round up tip?")
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp,
            onCheckedChange = onRoundUpChanged
        )
    }
}
