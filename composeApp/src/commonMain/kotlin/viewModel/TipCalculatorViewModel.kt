package viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.ceil
import kotlin.math.round

data class TipCalculatorUiState(
    val billAmount: String = "",
    val tipPercentage: String = "",
    val roundUp: Boolean = false,
    val tipResult: String = "$0.0"
)

class TipCalculatorViewModel : ViewModel() {
    private var isCleared = false
    private val _uiState = MutableStateFlow(TipCalculatorUiState())
    val uiState: StateFlow<TipCalculatorUiState> = _uiState.asStateFlow()

    init {
        println("TipCalculatorViewModel created")
        recalculateTip()
    }

    fun onBillAmountChange(value: String) {
        _uiState.update { current -> current.copy(billAmount = value) }
        recalculateTip()
    }

    fun onTipPercentageChange(value: String) {
        _uiState.update { current -> current.copy(tipPercentage = value) }
        recalculateTip()
    }

    fun onRoundUpChange(enabled: Boolean) {
        _uiState.update { current -> current.copy(roundUp = enabled) }
        recalculateTip()
    }

    private fun recalculateTip() {
        val current = _uiState.value
        val amount = current.billAmount.toDoubleOrNull() ?: 0.0
        val tipPercent = current.tipPercentage.toDoubleOrNull() ?: 0.0
        val result = calculateTip(amount = amount, tipPercent = tipPercent, roundUp = current.roundUp)
        _uiState.update { state -> state.copy(tipResult = result) }
    }

    private fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean): String {
        var tip = tipPercent / 100 * amount
        if (roundUp) {
            tip = ceil(tip)
        }
        val rounded = round(tip * 100) / 100
        return "$" + rounded.toString()
    }

    fun clear() {
        if (!isCleared) {
            isCleared = true
            onCleared()
        }
    }

    override fun onCleared() {
        println("TipCalculatorViewModel.onCleared()")
        super.onCleared()
    }
}
