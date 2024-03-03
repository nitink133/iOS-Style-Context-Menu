package club.stockgro.pocchatlongpress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import club.stockgro.pocchatlongpress.ui.theme.WhatsAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import club.stockgro.pocchatlongpress.ui.components.chat.ChatMessageList
import club.stockgro.pocchatlongpress.ui.components.chat.ChatScreenToolbar
import club.stockgro.pocchatlongpress.ui.components.chat.Message
import club.stockgro.pocchatlongpress.ui.components.chat.MessageInputField


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsAppTheme {
                AppContent()
            }
        }
    }
}


@Composable
fun AppContent() {
    Scaffold(
        topBar = {
            ChatScreenToolbar(
                onNavigationIconClick = { /* TODO */ },
                chatPartnerName = "John Doe",
                chatPartnerOnlineStatus = "last seen today at 11:00 AM",
                avatarPainter = painterResource(id = R.drawable.user_profile_picture)
            )
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            //            region Screen Background Image
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
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Transparent),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )
//endregion

            Spacer(modifier = Modifier.height(40.dp))

            // Your chat messages go here
            val sampleMessages = listOf(
                Message("Hello!", isSender = false),
                Message("Hi there! How are you?", isSender = true),
                Message("I'm good, thanks for asking. What about you?", isSender = false),
                Message("Doing well, just getting started with Compose.", isSender = true),
                Message("Oh, that's great to hear!", isSender = false),
                Message("Yeah, it's pretty fun once you get the hang of it.", isSender = true),
                Message("Have you built anything cool?", isSender = false),
                Message("Yes, I'm actually working on a chat app right now.", isSender = true),
                Message("That's awesome! I'd love to see it when it's done.", isSender = false),
                Message("Sure, I'll send you the GitHub link once I push it.", isSender = true)
            )

            Surface(color = Color.Transparent) {
                ChatMessageList(messages = sampleMessages)
            }

            // Message input field
            MessageInputField(modifier = Modifier.align(Alignment.BottomCenter))
        }

    }
}

@Preview
@Composable
fun PreViewAppContent() {
    WhatsAppTheme {
        AppContent()
    }
}