package com.example.todo

import android.annotation.SuppressLint
import androidx.activity.compose.setContent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Userinput(usermanager : dataman) {
    var desc by remember {
        mutableStateOf("")
    }
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var dialog by rememberSaveable {
        mutableStateOf(false)
    }


    Box(
        modifier = Modifier
            .padding(horizontal = 3.dp, vertical = 12.dp)
            .fillMaxSize(), Alignment.BottomEnd
    ) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp)
                    .background(Color.DarkGray),
                text = "To-Do App",
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
            ) {

                items(usermanager.users.size)
                { index ->
                    val token: Int = index

                    Row(modifier = Modifier.fillMaxSize()) {
                        Text(
                            modifier = Modifier
                                .background(Color.Cyan)
                                .width(330.dp)
                                .height(50.dp)
                                .clip(CircleShape)
                                .padding(10.dp)
                                .size(30.dp),
                            text = usermanager.users[index].desc

                        )

                        Button(
                            onClick = {

                                usermanager.users.removeAt(index )

                            },
                            shape = CircleShape,
                            ) {

                            Text(text = "x")
                        }


                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }


            }
        }
        Button(
            onClick = {
                isSheetOpen = true

            }) {
            Text(text = "+")
        }
    }
if (dialog) {
    ModalBottomSheet(modifier = Modifier
        .height(300.dp)
        .background(Color.LightGray), onDismissRequest = { dialog = false}) {
        Text(text = "You Want to delete")
        Button(onClick = { dialog = false }) {

            Text(text = "yes"
            )
        }
    }
}



    if (isSheetOpen) {
        ModalBottomSheet(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),

            onDismissRequest = {
                isSheetOpen = false

            }


        ) {
            TextField(
                value = desc,
                onValueChange = { desc = it },
                label = {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(CircleShape),
                        text = "Enter Task",
                        color = Color.Black
                    )


                })
            Spacer(modifier = Modifier.height(16.dp))
            Button(modifier = Modifier.padding(140.dp, 20.dp),
                onClick = {
                    usermanager.insertdata(desc)
                    isSheetOpen = false
                    desc = ""
                })
            {
                Text(text = "save task")
            }

        }
    }
}













