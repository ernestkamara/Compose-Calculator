import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var uiState by mutableStateOf(CalculatoruiState())
        private set
    val actions = listOf(
        CalculatorAction.Clear,
        CalculatorAction.Delete,
        CalculatorAction.Decimal,
        CalculatorAction.Operation(CalculatorOperation.Divide),
        CalculatorAction.Number(7),
        CalculatorAction.Number(8),
        CalculatorAction.Number(9),
        CalculatorAction.Operation(CalculatorOperation.Multiply),
        CalculatorAction.Number(4),
        CalculatorAction.Number(5),
        CalculatorAction.Number(6),
        CalculatorAction.Operation(CalculatorOperation.Minus),
        CalculatorAction.Number(1),
        CalculatorAction.Number(2),
        CalculatorAction.Number(3),
        CalculatorAction.Operation(CalculatorOperation.Plus),
        CalculatorAction.Number(0),
        CalculatorAction.Decimal,
        CalculatorAction.Calculate,
    )
    
    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> onNumberClick(action.number.toString())
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Calculate -> calculate()
            is CalculatorAction.Clear -> { uiState = CalculatoruiState()}
            CalculatorAction.Decimal -> enterDecimal()
            CalculatorAction.Delete -> delete()
        }
    }
 
    fun onNumberClick(number: String) {
        if (uiState.operation == null){
            if (uiState.number1.length >= MAX_NUM_LENGTH) return
            uiState = uiState.copy(number1 = uiState.number1 + number)
            return
        }
        
        if (uiState.number2.length >= MAX_NUM_LENGTH) return
        uiState = uiState.copy(number2 = uiState.number2 + number)
    }

    fun enterDecimal() {
        
        if (uiState.operation == null && !uiState.number1.contains(".") && uiState.number1.isNotBlank()) {
            uiState = uiState.copy(number1 = uiState.number1 + ".")
            return
        }

        if (!uiState.number2.contains(".") && uiState.number2.isNotBlank()) {
            uiState = uiState.copy(number2 = uiState.number2 + ".")
            return

        }
    }

    fun enterOperation(operation: CalculatorOperation) {
        if (uiState.number1.isNotBlank()) {
            uiState = uiState.copy(operation = operation)
        }
    }

    private fun delete() {
        when {
            uiState.number2.isNotBlank() -> uiState = uiState.copy(number2 = uiState.number2.dropLast(1))
            uiState.operation != null -> uiState = uiState.copy(operation = null)
            uiState.number1.isNotBlank() -> uiState = uiState.copy(number1 = uiState.number1.dropLast(1))
        }
    }


    fun calculate() {
        val number1 = uiState.number1.toDoubleOrNull()
        val number2 = uiState.number2.toDoubleOrNull()
        val result = when(uiState.operation){
            CalculatorOperation.Divide -> number1?.div(number2 ?: 0.0)
            CalculatorOperation.Minus -> number1?.minus(number2 ?: 0.0)
            CalculatorOperation.Multiply ->  number1 ?.times(number2 ?: 0.0)
            CalculatorOperation.Plus -> number1?.plus(number2 ?: 0.0)
            null -> return
        }
        uiState = uiState.copy(
            number1 = result.toString().take(MAX_NUM_LENGTH),
            number2 = "",
            operation = null
        )
    }
    
    companion object{
        private const val MAX_NUM_LENGTH = 15
    }
}