import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen(
    state: CalculatoruiState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit = {},
    actions: List<CalculatorAction>,
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val isPortrait = maxWidth < maxHeight
        if (isPortrait) {
            PortraitCalculatorLayout(state, buttonSpacing, onAction, actions)
        } else {
            LandscapeCalculatorLayout(state, buttonSpacing, onAction, actions)
        }
    }
}

@Composable
fun PortraitCalculatorLayout(
    state: CalculatoruiState,
    buttonSpacing: Dp,
    onAction: (CalculatorAction) -> Unit,
    actions: List<CalculatorAction>,
) {
    Column(
        //modifier = Modifier.align(Alignment.BottomCenter),
        verticalArrangement = Arrangement.spacedBy(buttonSpacing),
    ) {
        DisplayText(
            state,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
        )
        CalculatorGrid(
            onAction = onAction,
            actions = actions,
            isPortrait = true,
            buttonSpacing = buttonSpacing,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun LandscapeCalculatorLayout(
    state: CalculatoruiState,
    buttonSpacing: Dp,
    onAction: (CalculatorAction) -> Unit,
    actions: List<CalculatorAction>,
    modifier: Modifier = Modifier,
) {
    Column(
        //modifier = Modifier.align(Alignment.BottomCenter),
        //verticalArrangement = Arrangement.spacedBy(buttonSpacing),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DisplayText(
            state,
            modifier = Modifier
                .weight(1f)
                //.align(Alignment.CenterHorizontally)
        )
        CalculatorGrid(
            onAction = onAction,
            buttonSpacing = buttonSpacing,
            actions = actions,
            isPortrait = false,
            modifier = modifier
                .weight(3f)
                //.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun DisplayText(
    state: CalculatoruiState,
    modifier: Modifier = Modifier
) {
    Text(
        text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
        textAlign = TextAlign.End,
        fontWeight = FontWeight.Light,
        fontSize = 80.sp,
        color = Color.White,
        maxLines = 4,
        modifier = modifier
            .padding(vertical = 38.dp)
            .fillMaxWidth()
    )
}

@Composable
fun CalculatorGrid(
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit = {},
    actions: List<CalculatorAction> = emptyList(),
    isPortrait: Boolean,
    modifier: Modifier = Modifier
) {
    val mediumGrey = Color(0xFF2E2E2E)
    val orange = Color(0xFFFF9800)
    val lightGrey = Color(0xFF818181)
    val chunkedSize = if (isPortrait) 4 else 6
    for (row in actions.chunked(chunkedSize)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            for (action in row) {
                val backgroundColor =
                    when (action) {
                        is CalculatorAction.Number, is CalculatorAction.Decimal -> mediumGrey
                        is CalculatorAction.Operation -> lightGrey
                        else -> orange
                    }
    
                val (symbol, ratio) = when (action) {
                    is CalculatorAction.Number -> Pair(action.number.toString(), 1f)
                    is CalculatorAction.Operation -> Pair(action.operation.symbol, 1f)
                    CalculatorAction.Calculate -> Pair("=", 2f)
                    CalculatorAction.Clear -> Pair("AC", 1f)
                    CalculatorAction.Decimal -> Pair(".", 2f)
                    CalculatorAction.Delete -> Pair("Del", 1f)
                }
    
                CalculatorButton(
                    symbol = symbol,
                    onClick = { onAction(action) },
                    modifier = modifier.background(backgroundColor)
                        //.weight(0.9f)
                        //.aspectRatio(0.9f)
                        .padding(4.dp)
                )
            }
        }
    }
}
