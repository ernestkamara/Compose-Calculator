data class CalculatoruiState(
    val number1: String = "",
    val number2: String = "",
    val operation: CalculatorOperation? = null,
)

sealed class CalculatorState {
    object Idle : CalculatorState()
    data class Result(val value: Double) : CalculatorState()
    data class Error(val message: String) : CalculatorState()
} 