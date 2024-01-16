package com.chintek.example.asrdemo.presentation.stt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chintek.example.asrdemo.domain.chat.Message
import com.chintek.example.asrdemo.presentation.common.ChatView
import com.chintek.example.asrdemo.presentation.common.ErrorDialog
import com.chintek.example.asrdemo.ui.theme.ASRDemoTheme

@Composable
fun STTScreen(
    viewModel: STTViewModel = hiltViewModel()
) {
    val state = viewModel.state
    STTView(
        state.messages,
        state.spokenText,
        { viewModel.updateText(it) },
        { viewModel.voiceInput() },
        { viewModel.send() },
    )
    ErrorDialog(contentMsg = state.error, onDismiss = {})
}

@Composable
fun STTView(
    messages: List<Message> = listOf(),
    spokenText: String = "",
    onSpokenTextChanged: (String) -> Unit = {},
    onVoiceInputClick: () -> Unit = {},
    onSendClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxSize().weight(1f)
                .background(color = Color.White)
        ) {
            ChatView(
                messages = messages,
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
            )
            IconButtonRow(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter), imageVector = Icons.Rounded.Mic, onVoiceInputClick)
        }

        SendTextBar(spokenText, onSpokenTextChanged, onSendClick)
    }
}

@Composable
fun IconButtonRow(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit = {}
) {
    Row(modifier = modifier.padding(20.dp)) {
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            onClick = onClick,
            shape = CircleShape,
            modifier = Modifier.size(40.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Red)
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendTextBar(
    spokenText: String,
    onSpokenTextChanged: (String) -> Unit = {},
    onSendClick: () -> Unit,
) {
    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.background.copy(0.5f)).fillMaxWidth().padding(20.dp)) {
        Text(
            text = "輸入你想說的話",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            textAlign = TextAlign.Start,
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = spokenText,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.background.copy(0.5f),
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
            ),
            onValueChange = onSpokenTextChanged,
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = {
                if (spokenText.isNotEmpty()) {
                    IconButton(onClick = onSendClick) {
                        Icon(
                            imageVector = Icons.Outlined.Send,
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun SendTextBarPrev() {
    ASRDemoTheme() {
        SendTextBar("Hello", {}, {})
    }
}

@Preview
@Composable
fun STTViewPrev() {
    ASRDemoTheme {
        STTView()
    }
}