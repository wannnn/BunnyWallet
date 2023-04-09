package com.cj.bunnywallet.feature.managecrypto.components

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier
) {

    var text by remember { mutableStateOf("") }
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = text,
        onValueChange = { text = it },
        textStyle = TextStyle(fontSize = 15.sp),
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp),
        interactionSource = interactionSource,
        enabled = true,
        singleLine = true
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = text,
            innerTextField = it,
            singleLine = true,
            enabled = true,
            visualTransformation = VisualTransformation.None,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_hint),
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            interactionSource = interactionSource,
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            },
            shape = RoundedCornerShape(32.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                top = 0.dp, bottom = 0.dp, start = 0.dp
            )
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewSearchTextField() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SearchTextField()
        }
    }
}