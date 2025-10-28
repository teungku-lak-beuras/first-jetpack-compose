package hemel.van.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnBoarding by rememberSaveable {mutableStateOf(true)}

    Surface(modifier) {
        if (shouldShowOnBoarding) {
            OnboardingScreen(
                continueCallback = {shouldShowOnBoarding = false},
                modifier = modifier
            )
        }
        else {
            Greetings(
                modifier = modifier
            )
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(
        modifier = modifier
            .padding(vertical = 4.dp)
    ) {
        items(items = names) { name ->
            Greeting(name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by rememberSaveable {mutableStateOf(false)}
    val expandableColor by animateColorAsState(
        if (expanded) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primary
    )
    val expandablePadding by animateDpAsState(
        targetValue = if (expanded) 128.dp else 32.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = expandableColor,
        border = if (expanded) BorderStroke(1.dp, MaterialTheme.colorScheme.primary) else null,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(
                vertical = 4.dp,
                horizontal = 8.dp
            )
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = expandablePadding)
            ) {
                Text(
                    text = "Hello!",
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(text = name)
            }
            ElevatedButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    continueCallback: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the basics Code laboratorium")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = continueCallback
        ) {
            Text("Continue")
        }
    }
}
