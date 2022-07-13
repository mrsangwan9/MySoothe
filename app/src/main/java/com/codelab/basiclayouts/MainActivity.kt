package com.codelab.basiclayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basiclayouts.ui.theme.MySootheTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MySootheApp() }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
 TextField(
     value ="" ,
     onValueChange ={},
     modifier = modifier
         .heightIn(min = 56.dp)
         .fillMaxWidth(),
     leadingIcon = {
         Icon(
             imageVector = Icons.Default.Search,
             contentDescription = null
         )
     },
    placeholder = {Text(stringResource(R.string.Serach_placeholder))},

    colors = TextFieldDefaults.textFieldColors(
        backgroundColor = colors.surface
    )
 )
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable:Int,
    @StringRes text:Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment=Alignment.CenterHorizontally,
        modifier=modifier) {
        Image(
            painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier= modifier
                .size(88.dp)
                .clip(CircleShape)
        )

        Text(
            stringResource(text),
            style = MaterialTheme.typography.h3,
            modifier = modifier.paddingFromBaseline(
                top= 20.dp, bottom = 5.dp
            )
        )
    }

}
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
     ) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp) ,
        contentPadding = PaddingValues(
            horizontal = 16.dp
        ),
        modifier=modifier
    ){
        items(alignYourBodyData){
            item->
                AlignYourBodyElement(drawable = item.drawable, text =item.text )
        }
    }
}

// Step: Favorite collection card - Material Surface
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable:Int,
    @StringRes text:Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row(Modifier.width(192.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.size(56.dp)
            )

            Text(
                stringResource(text),
                style = MaterialTheme.typography.h3
            )
        }
    }
    }

// Step: Favorite collections grid - LazyGrid
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
           rows = GridCells.Fixed(2),
            modifier= modifier.height(120.dp),
           contentPadding= PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
           horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(favoriteCollectionsData){
                item->
            FavoriteCollectionCard(drawable = item.drawable, text =item.text )
        }
    }
    }
// Step: Home section - Slot APIs
@Composable
fun HomeSection(
    @StringRes tittle:Int,
    content:@Composable ()->Unit,
    modifier: Modifier = Modifier
) {
    Column() {
        Text(stringResource(tittle).uppercase(),
        style = MaterialTheme.typography.h2,
            modifier = modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(16.dp)
        )
        content()

}
}
// Step: Home screen - Scrolling
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column (Modifier.verticalScroll(rememberScrollState())){
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(modifier.padding(horizontal = 15.dp))
        HomeSection(tittle =(R.string.align_your_body), { AlignYourBodyRow() })
        HomeSection(tittle = (R.string.favorite_collections), { FavoriteCollectionsGrid()})
        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Step: Bottom navigation - Material
@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    BottomNavigation(
        backgroundColor=MaterialTheme.colors.background,
        modifier = modifier,
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon=
            {
                Icon(Icons.Default.Spa,contentDescription=null)
            },
            label = {Text(stringResource(R.string.bottom_navigation_home)) })

    BottomNavigationItem(
            selected = true,
    onClick = { /*TODO*/ },
    icon=
        {
            Icon(Icons.Default.AccountCircle,contentDescription=null)
        },
    label = {Text(stringResource(R.string.bottom_navigation_profile)) })
}
}

// Step: MySoothe App - Scaffold


@Composable
fun MySootheApp() {
    Scaffold(
        bottomBar = { SootheBottomNavigation()}
    ) {
        padding->
        HomeScreen()
    }

}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)


@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun SearchBarPreview() {
    MySootheTheme { SearchBar(Modifier.padding(8.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyElementPreview() {
    MySootheTheme {
        AlignYourBodyElement(
            drawable= R.drawable.ab1_inversions,
            text = R.string.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyRowPreview() {
    MySootheTheme { AlignYourBodyRow() }
}
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionCardPreview() {
    MySootheTheme {
        FavoriteCollectionCard(
            drawable=R.drawable.fc2_nature_meditations,
            text = R.string.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionsGridPreview() {
    MySootheTheme { FavoriteCollectionsGrid() }
}


@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeSectionPreview() {
    MySootheTheme { HomeSection(tittle = R.string.align_your_body, { AlignYourBodyRow() })}
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ScreenContentPreview() {
    MySootheTheme { HomeScreen() }
}
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun BottomNavigationPreview() {
    MySootheTheme { SootheBottomNavigation(Modifier.padding(top = 24.dp)) }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun MySoothePreview() {
    MySootheTheme() {
        MySootheApp()
}}
