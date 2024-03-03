package club.stockgro.pocchatlongpress.ui.components.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Message(val text: String, val isSender: Boolean)

@Composable
fun ChatMessageList(messages: List<Message>) {
    LazyColumn {
        itemsIndexed(items = messages) { index, message ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = if (index == 0) 8.dp else 0.dp) // Add padding to the first item
            ) {
                ChatBubble(message = message.text, isSender = message.isSender)
            }
        }
    }
}

@Composable
fun ChatBubble(message: String, isSender: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = if (isSender) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            modifier = Modifier.wrapContentWidth(),
            shape = RoundedCornerShape(12.dp),
            color = if (isSender) Color(0xFFDCF8C6) else Color(0xFFFFFFFF),
            shadowElevation = 1.dp
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(all = 8.dp),
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun PreviewChatBubble() {
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

    Surface(color = MaterialTheme.colorScheme.background) {
        ChatMessageList(messages = sampleMessages)
    }
}
