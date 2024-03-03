package club.stockgro.pocchatlongpress.ui.components.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import club.stockgro.pocchatlongpress.R

@Composable
fun ChatScreenBackground(
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_chat),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop, // Cover the entire screen area
            modifier = Modifier.matchParentSize()
        )
        // Apply a translucent overlay if necessary to ensure text readability
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Transparent),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                ))
        )
        // Your chat screen content goes here
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatScreenBackground() {
    ChatScreenBackground(
    ) {
        // Replace with your chat UI content
    }
}
