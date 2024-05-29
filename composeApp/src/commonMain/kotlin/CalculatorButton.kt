import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
)  {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable {onClick()}
            .clip(CircleShape)
            .then(modifier)
    ) {
        Text(
            text = symbol,
            color = Color.White,
            fontSize = 32.sp
        )
    }
}

@Preview
@Composable
fun calculatorButtonPreview() {
    CalculatorButton("1") {
    }
    
}