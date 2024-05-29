import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel: CalculatorViewModel = viewModel { CalculatorViewModel() }
        val uiState = viewModel.uiState
        val actions = viewModel.actions
        CalculatorScreen(
            state = uiState,
            onAction = viewModel  ::onAction,
            actions = actions,
            modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp)
        )
    }
}

