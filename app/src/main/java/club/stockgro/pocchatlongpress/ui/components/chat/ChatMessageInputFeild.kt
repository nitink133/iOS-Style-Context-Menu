package club.stockgro.pocchatlongpress.ui.components.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import club.stockgro.pocchatlongpress.R
import club.stockgro.pocchatlongpress.ui.theme.WhatsAppTheme

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MessageInputField(modifier: Modifier) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    // Define the colors based on your theme or as they are in WhatsApp
    val backgroundColor = Color.Transparent // WhatsApp input field background color
    val sendButtonColor = MaterialTheme.colorScheme.primaryContainer // WhatsApp send button color

    Surface(
        modifier = modifier.padding(bottom = 6.dp),
        color = Color.Transparent, // Use a transparent color if you don't want any background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Emoji icon on the left inside the input field
            Icon(
                painter = painterResource(id = R.drawable.ic_emoji), // Replace with actual emoji icon
                contentDescription = "Emoji",
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(24.dp),
                tint = Color.DarkGray
            )

            // Input field
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White, RoundedCornerShape(18.dp))
                    .padding(horizontal = 12.dp, vertical = 0.dp)
            ) {
                TextField(
                    value = text,
                    onValueChange = { newText -> text = newText },
                    placeholder = { Text("Type a message") },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color.DarkGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = {
                        // TODO: Send message action
                        text = ""
                        keyboardController?.hide()
                    }),
                    trailingIcon = {
                        if (text.isNotEmpty()) {
                            IconButton(onClick = {
                                // TODO: Send message action
                                text = ""
                                keyboardController?.hide()
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Send,
                                    contentDescription = "Send",
                                    tint = sendButtonColor
                                )
                            }
                        } else {
                            // Camera icon on the right inside the input field
                            Icon(
                                imageVector = Icons.Filled.Call,
                                contentDescription = "Camera",
                                tint = Color.DarkGray
                            )
                        }
                    }
                )
            }

            // Send button (floating to the right of the input field)
            val sendIconTint = if (text.isNotEmpty()) Color.White else sendButtonColor
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(if (text.isNotEmpty()) sendButtonColor else Color.Transparent)
                    .padding(12.dp)
            ) {
                IconButton(onClick = {
                    // TODO: Send message action
                    text = ""
                    keyboardController?.hide()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Send",
                        tint = sendIconTint
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMessageInputField() {

    WhatsAppTheme {
        Box() {
            // Your chat screen content goes here
            MessageInputField(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}
