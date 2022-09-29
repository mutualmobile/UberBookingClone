package com.example.uberbookingexperience.ui.screens.whereTo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextOverflow.Companion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.theme.ClearIconTint
import com.example.uberbookingexperience.ui.theme.ContentTextStyle
import com.example.uberbookingexperience.ui.theme.PlaceholderTextStyle
import com.example.uberbookingexperience.ui.theme.SelectedBgColor
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.UnselectedBgColor
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.clickableWithRipple

private val TfMinHeight = 48.dp

@Composable
fun UberTextField(
  modifier: Modifier = Modifier,
  value: String,
  onValueChange: (String) -> Unit,
  contentPadding: PaddingValues = PaddingValues(
    horizontal = MaterialTheme.spacing.medium,
    vertical = MaterialTheme.spacing.extraSmall
  ),
  placeholder: String,
  tfFocusChanged: () -> Unit
) {
  var isTfFocused by rememberSaveable { mutableStateOf(false) }
  val bgColor by animateColorAsState(targetValue = if (isTfFocused) SelectedBgColor else UnselectedBgColor)

  Box(
    modifier = modifier
      .background(bgColor)
      .defaultMinSize(minHeight = TfMinHeight),
    contentAlignment = Alignment.CenterStart
  ) {
    AnimatedVisibility(
      visible = value.isEmpty(),
      enter = fadeIn(),
      exit = fadeOut()
    ) {
      Text(
        modifier = Modifier
          .fillMaxWidth()
          .padding(contentPadding),
        text = placeholder,
        style = PlaceholderTextStyle,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
    }
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      BasicTextField(
        modifier = Modifier
          .weight(5f)
          .padding(contentPadding)
          .onFocusChanged {
            isTfFocused = it.isFocused
            tfFocusChanged()
          },
        value = value,
        onValueChange = onValueChange,
        textStyle = ContentTextStyle
      )
      AnimatedVisibility(
        modifier = Modifier
          .weight(1f)
          .clip(CircleShape),
        visible = value.isNotBlank(),
        enter = fadeIn(),
        exit = fadeOut()
      ) {
        Icon(
          modifier = Modifier.clickableWithRipple {
            onValueChange("")
          },
          imageVector = Icons.Default.Close,
          contentDescription = null,
          tint = ClearIconTint
        )
      }
    }
  }
}

// @Preview(showSystemUi = true)
// @Composable
// private fun WhereToScreenPreview() {
//   UberBookingExperienceTheme {
//     Column(modifier = Modifier.fillMaxSize()) {
//       var tf1Value by rememberSaveable {
//         mutableStateOf("")
//       }
//       var tf2Value by rememberSaveable {
//         mutableStateOf("")
//       }
//       UberTextField(
//         modifier = Modifier.padding(horizontal = 32.dp),
//         placeholder = "Enter pickup location",
//         value = tf1Value,
//         onValueChange = { newTf1Value ->
//           tf1Value = newTf1Value
//         },
//         tfFocusChanged = {}
//       )
//       Spacer(modifier = Modifier.padding(top = 8.dp))
//       UberTextField(
//         modifier = Modifier.padding(horizontal = 32.dp),
//         placeholder = "Where to?",
//         value = tf2Value,
//         onValueChange = { newTf2Value ->
//           tf2Value = newTf2Value
//         },
//         tfFocusChanged = {}
//       )
//     }
//   }
// }