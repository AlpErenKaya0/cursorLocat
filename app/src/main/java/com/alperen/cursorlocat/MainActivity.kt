package com.alperen.cursorlocat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alperen.cursorlocat.ui.theme.CursorLocatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CursorLocatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContentView()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun   ContentView() {
    val focusManager = LocalFocusManager.current
   val keyboardController = LocalSoftwareKeyboardController.current
   // val text = remember {
     //   mutableStateOf("")
    //}
    val textFieldValue = remember {
        mutableStateOf(
            TextFieldValue(
                text="",
                selection = TextRange(0)
            )
        )
    }
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray
            ) {
        Column(modifier = Modifier
            .fillMaxSize()) {
BasicTextField(value = textFieldValue.value,
    onValueChange = {
textFieldValue.value = TextFieldValue(
    text = it.text,
    selection = TextRange(it.text.length)
)
},
modifier = Modifier
    .padding(horizontal = 10.dp)
    .padding(top = 30.dp)
    .fillMaxWidth()
    .wrapContentHeight()
    .clip(RoundedCornerShape(8.dp))
    .background(Color.Gray),
    maxLines = 1,
    decorationBox = {innerTextField ->

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            innerTextField()
        }
    },
    cursorBrush = SolidColor(Color.White),
    textStyle = TextStyle(
        color = Color.White,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold
    ),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
    keyboardActions = KeyboardActions(
        onDone = {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    )

)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CursorLocatTheme {
        ContentView()
    }
}