sealed class CalculatorAction {
    data class Number(val number: Int) : CalculatorAction()
    data object Clear : CalculatorAction()
    data object Delete : CalculatorAction()
    data object Decimal : CalculatorAction()
    data object Calculate : CalculatorAction()
    data class Operation(val operation: CalculatorOperation) : CalculatorAction()
}

sealed class CalculatorOperation(val symbol: String) {
    data object Plus : CalculatorOperation("+")
    data object Minus : CalculatorOperation("-")
    data object Multiply : CalculatorOperation("*")
    data object Divide : CalculatorOperation("/")
}

    