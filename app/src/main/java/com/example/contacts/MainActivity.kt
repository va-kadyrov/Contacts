package com.example.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val font20bold = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent @OptIn(ExperimentalMaterial3Api::class) {
                        ContactDetails(getContactExample(2))
    }}
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ContactDetails(contact: Contact) {
    Column {
        TopAppBar(
            title = { Text(stringResource(R.string.first_compose_project))},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0x62, 0x00, 0xEE),
                titleContentColor = Color.White)
        )
        ContactImage(contact)
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = contact.name + " " + (contact.surname ?: ""),
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Row(modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 40.dp)){
            Text(
                text = contact.familyName,
                style = TextStyle(fontSize = 24.sp))
            if (contact.isFavorite) Image(
                modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically),
                painter = painterResource(id = android.R.drawable.star_big_on),
                contentDescription = null)
        }
        InfoRow(stringResource(R.string.phone), contact.phone)
        InfoRow(stringResource(R.string.address), contact.address)
        if (contact.email != null) InfoRow(stringResource(R.string.email), contact.email)
    }
}

@Composable
fun InfoRow(name: String, value: String) {
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
        Column(
            modifier = Modifier.weight(1F),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$name: ",
                style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 16.sp)
            )
        }
        Column(
            modifier = Modifier.weight(1F),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = value,
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}

@Composable
fun ContactImage(contact: Contact) {
    Box(modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
        if (contact.imageRes == null) {
            Icon(
                painter = painterResource(R.drawable.circle),
                contentDescription = "АВ",
                tint = Color(0xCC, 0xCC, 0xCC),
                modifier = Modifier.size(64.dp)
            )
        Text(
            text = contact.name.take(1) + contact.familyName.take(1),
            style = font20bold
        )
    } else {
        Image(
            modifier = Modifier.size(120.dp, 80.dp),
            painter = painterResource(contact.imageRes),
            contentDescription = "Avatar",
            contentScale = ContentScale.FillWidth
        )}
    }
}


@Composable
@Preview
fun ContactDetailPreview1() {
    ContactDetails(getContactExample(1))
}

@Composable
@Preview
fun ContactDetailPreview2() {
    ContactDetails(getContactExample(2))
}


fun getContactExample(i: Int) =
    when (i){
        1 -> {
            Contact("Евгений",
                "Андреевич",
                "Лукашин",
                null,
                true,
                "+7 495 495 95 95",
                "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                "Elukashin@practicum.ru")
        }
        2 -> {
            Contact("Василий",
            null,
            "Кузякин",
            R.drawable.luna3,
            false,
            "---",
            "Ивановская область, дер. Крутово, д. 4",
            null)
        }
        else -> {
            Contact("-",
                "-",
                "-",
                null,
                false,
                "-",
                "-",
                "-")
    }
}