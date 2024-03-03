package club.stockgro.pocchatlongpress.ui.components.chat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import club.stockgro.pocchatlongpress.R
import club.stockgro.pocchatlongpress.ui.theme.WhatsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreenToolbar(
    onNavigationIconClick: () -> Unit,
    chatPartnerName: String,
    chatPartnerOnlineStatus: String,
    avatarPainter: Painter
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = avatarPainter,
                    contentDescription = "Chat Partner Avatar",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = chatPartnerName,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.surface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = chatPartnerOnlineStatus,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.surface
                )
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Handle voice call click */ }) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = "Voice Call",
                    tint = MaterialTheme.colorScheme.surface
                )
            }
            IconButton(onClick = { /* TODO: Handle more options click */ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More Options",
                    tint = MaterialTheme.colorScheme.surface
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewChatScreenToolbar() {
    WhatsAppTheme {
        ChatScreenToolbar(
            onNavigationIconClick = { /* TODO */ },
            chatPartnerName = "Loretta Fleming",
            chatPartnerOnlineStatus = "last seen today at 11:00 AM",
            avatarPainter = painterResource(id = R.drawable.user_profile_picture)
        )
    }
}

