package club.stockgro.pocchatlongpress.ui.components.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import club.stockgro.pocchatlongpress.R
import club.stockgro.pocchatlongpress.ui.theme.WhatsAppTheme

@Composable
fun ChatBubbleContextMenu(
    message: String,
    isSender: Boolean,
    showMenu: Boolean,
    onDismissRequest: () -> Unit,
    menuItems: List<ContextMenuItem>,
    modifier: Modifier = Modifier,
    offset: IntOffset
) {
    if (showMenu) {
        Popup(
            onDismissRequest = onDismissRequest,
            properties = PopupProperties(focusable = true, dismissOnClickOutside = true),
            offset = offset
        ) {

            Column(modifier = Modifier.wrapContentSize()) {
                ChatBubble(message = message, isSender = isSender, isPopupItem = true, onContextMenuVisibilityChange = {
                    //onDismissRequest()
                })

                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = modifier
                        .background(Color.Transparent)
                        .width(230.dp)
                        .padding(0.dp)

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xF5FFFFFF))
                    ) {
                        menuItems.forEachIndexed { index, item ->
                            ContextMenuEntry(item = item, onClick = {
                                item.onClick()
                                onDismissRequest()
                            })
                            if (index < menuItems.size - 1) {
                                Divider(
                                    thickness = .8.dp,
                                    modifier = Modifier.background(color = Color(0XFF555555))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContextMenuEntry(
    item: ContextMenuItem,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.text,
            modifier = Modifier.size(20.dp),
            tint = Color(0xFF222222)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = item.text, color = Color(0xFF222222))
    }
}

data class ContextMenuItem(
    val text: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatBubbleWithContextMenu(message: String) {
    var showMenu by remember { mutableStateOf(false) }
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

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = message,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .combinedClickable(onLongClick = { showMenu = true }) {}
        )
//        ChatBubbleContextMenu(
//            showMenu = true,
//            onDismissRequest = { showMenu = false },
//            menuItems = menuItems,
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
    }
}

@Composable
@Preview
fun PreviewChatBubbleWithContextMenu() {
    WhatsAppTheme {
        ChatBubbleWithContextMenu("Long press me for options")
    }
}

