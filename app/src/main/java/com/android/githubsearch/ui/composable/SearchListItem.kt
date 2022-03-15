package com.android.githubsearch.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.android.githubsearch.ui.model.SearchItem
import com.android.githubsearch.utils.Constants.CARD_ITEM_TEXT_TAG

@ExperimentalCoilApi
@Composable
fun SearchListItem(searchItem: SearchItem) {
    Card(
        modifier = Modifier
            .testTag(CARD_ITEM_TEXT_TAG)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 8.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(8.dp))
            Image(modifier = Modifier.size(64.dp).clip(RoundedCornerShape(8.dp)), painter = rememberImagePainter(searchItem.avatarUrl), contentDescription = "Avatar Image")
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                Modifier
                    .padding(16.dp)
            ) {
                Text(text = searchItem.login, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = searchItem.type, fontSize = 14.sp, color = Color.Blue)
            }
        }
    }
}
