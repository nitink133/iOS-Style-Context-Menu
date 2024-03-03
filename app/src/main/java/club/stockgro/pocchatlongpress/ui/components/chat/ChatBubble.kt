package club.stockgro.pocchatlongpress.ui.components.chat

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties
import club.stockgro.pocchatlongpress.R
import kotlin.math.roundToInt

data class Message(val text: String, val isSender: Boolean)

@Composable
fun ChatMessageList(messages: List<Message>, onContextMenuVisibilityChange: (Boolean) -> Unit) {
    LazyColumn {
        itemsIndexed(items = messages) { index, message ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = if (index == 0) 8.dp else 0.dp) // Add padding to the first item
            ) {
                ChatBubble(
                    message = message.text,
                    isSender = message.isSender,
                    onContextMenuVisibilityChange = { action ->
                        // Handle long press action here
                        onContextMenuVisibilityChange(action)
                    })
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatBubble(
    message: String,
    isSender: Boolean,
    isPopupItem: Boolean = false,
    onContextMenuVisibilityChange: (Boolean) -> Unit
) {
    val showMenu = remember { mutableStateOf(false) }
    val menuItems = listOf(
        ContextMenuItem(
            "Reply",
            ImageVector.vectorResource(id = R.drawable.ic_reply)
        ) { /* Handle Material item click */ },
        ContextMenuItem(
            "Forward",
            ImageVector.vectorResource(id = R.drawable.ic_forward)
        ) { /* Handle Custom text field item click */ },
        ContextMenuItem(
            "Copy Message",
            ImageVector.vectorResource(id = R.drawable.ic_copy)
        ) { /* Handle Custom icons click */ },
        ContextMenuItem(
            "Edit Message",
            ImageVector.vectorResource(id = R.drawable.ic_edit)
        ) { /* Handle Long group click */ },
        ContextMenuItem(
            "Delete",
            ImageVector.vectorResource(id = R.drawable.ic_delete)
        ) { /* Handle Nested group click */ },
    )

    val menuPosition = remember { mutableStateOf(Offset.Zero) }
    val density = LocalDensity.current
    val messageBounds = remember { mutableStateOf(Rect.Zero) }

    val context = LocalContext.current
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    Box(
        modifier = if (isPopupItem)
            Modifier
                .wrapContentSize()
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .wrapContentWidth(if (isSender) Alignment.End else Alignment.Start)
        else Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .wrapContentWidth(if (isSender) Alignment.End else Alignment.Start)
    ) {
        Surface(
            modifier = Modifier
                .combinedClickable(onClick = {
                    // Handle click action here
                }, onLongClick = {
                    // Perform haptic feedback
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(
                            VibrationEffect.createOneShot(
                                50,
                                VibrationEffect.DEFAULT_AMPLITUDE
                            )
                        )
                    } else {
                        @Suppress("DEPRECATION")
                        vibrator.vibrate(50)
                    }

                    showMenu.value = true
                    onContextMenuVisibilityChange(showMenu.value)
                    // Use the density and bounds to calculate the exact position
                    val positionInParent = messageBounds.value.topLeft
                    menuPosition.value = Offset(
                        x = if(isSender) positionInParent.x else positionInParent.x - 16.dp.value,
                        y = positionInParent.y  - 12.dp.value
                    )
                })
                .onGloballyPositioned { coordinates ->
                    messageBounds.value = coordinates.boundsInParent()
                },
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

        ChatBubbleContextMenu(
            message = message,
            isSender = isSender,
            showMenu = showMenu.value,
            onDismissRequest = {
                showMenu.value = false
                onContextMenuVisibilityChange(showMenu.value)
            },
            menuItems = menuItems,
            modifier = Modifier.align(Alignment.CenterEnd),
            offset = IntOffset(
                x = menuPosition.value.x.roundToInt(),
                y = menuPosition.value.y.roundToInt()
            )
        )
    }
}

@Preview(showBackground = true)
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
        ChatMessageList(messages = sampleMessages) {}
    }
}
