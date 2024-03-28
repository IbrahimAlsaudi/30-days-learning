package com.example.a30daysoflearning

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.a30daysoflearning.data.DaysRepository.days
import com.example.a30daysoflearning.model.Day

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ThirtyDaysApp() {
    Scaffold(
        topBar = { MyTopBar() }
    ) {
        CardsList(Modifier.padding(it))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
        Text(
            text = stringResource(R.string.app_bar),
            style = MaterialTheme.typography.displayLarge
        )
    })
}


@Composable
fun CardsList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(days){
            DayCard(day = it)
        }
    }
}

@Composable
private fun CardIcon(
    onClick: () -> Unit,
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = null
        )
    }
}
@Composable
fun DayCard(day: Day, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.tertiaryContainer,
        label = ""
    )
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.padding(
            vertical = dimensionResource(id = R.dimen.padding_extra_small),
            horizontal = dimensionResource(id = R.dimen.padding_semi_medium)
        )
//            .padding(horizontal = 16.dp, vertical = 4.dp)

    ) {
        Column(
            modifier = Modifier
//                .padding(8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .background(color)
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row (verticalAlignment = Alignment.Top){
                Text(
                    text = stringResource(id = R.string.day, day.dayNumber),
                    style = MaterialTheme.typography.displaySmall,
                    color = if (expanded) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
                )
                Spacer(modifier = Modifier.weight(1f))
                CardIcon(
                    expanded = expanded,
                    onClick = {expanded = !expanded }
                )
            }
                Text(
                    text = stringResource(id = day.titleRes),
                    style = MaterialTheme.typography.displayMedium,
                    color = if (expanded) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onPrimaryContainer,
                )

            Image(
                painter = painterResource(id = day.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_small)))
                    .size(230.dp)
                ,
                contentScale = ContentScale.Crop
            )
            if (expanded) {
                Text(
                    text = stringResource(id = day.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    color = if (expanded) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_extra_small))

                )
            }
        }
    }
}