package com.chintek.example.asrdemo.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chintek.example.asrdemo.domain.chat.Message
import com.chintek.example.asrdemo.domain.chat.Role
import com.chintek.example.asrdemo.ui.theme.ASRDemoTheme

@Composable
fun ChatView(
    messages: List<Message>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(Color.Transparent)
    ) {
        val listState = rememberLazyListState()
        LaunchedEffect(messages.size) {
            listState.animateScrollToItem(messages.size)
        }
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            items(messages) { item ->
                ChatItem(item)
            }
        }
    }
}

@Composable
fun ChatItem(message: Message) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Box(
            modifier = Modifier
                .align(if (message.role is Role.User) Alignment.End else Alignment.Start)
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = if (message.role is Role.User) 48f else 0f,
                        bottomEnd = if (message.role is Role.User) 0f else 48f
                    )
                )
                .background(if (message.role is Role.User) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            Text(
                text = message.text,
                fontWeight = FontWeight.Bold,
                color = if (message.role is Role.User) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatItemPrev() {
    ASRDemoTheme() {
        Column() {
            ChatItem(message = Message(Role.User(), "Hello"))
            ChatItem(message = Message(Role.System(), "Hello"))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ChatViewPrev() {
    ASRDemoTheme() {
        ChatView(
            messages = mutableListOf<Message>().apply {
                add(Message(Role.User(), "Hi"))
                add(Message(Role.System(), "Hi1"))
                add(Message(Role.User(), "Hi2"))
                add(Message(Role.System(), "Hi3"))
                add(Message(Role.User(), "Hi"))
                add(Message(Role.System(), "Hi1"))
                add(Message(Role.User(), "Hi2"))
                add(Message(Role.System(), "Hi3"))
                add(Message(Role.User(), "Hi"))
                add(Message(Role.System(), "Hi1"))
                add(Message(Role.User(), "Hi2"))
                add(Message(Role.System(), "Hi3"))
            })
    }
}