package com.vatsal.cibc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vatsal.cibc.RoutePath.DETAIL_ROUTE
import com.vatsal.cibc.RoutePath.LANDING_ROUTE
import com.vatsal.cibc.model.Account
import com.vatsal.cibc.ui.theme.CIBCTheme
import com.vatsal.cibc.viewmodel.LandingScreenUiState
import com.vatsal.cibc.viewmodel.LandingScreenUiState.*
import com.vatsal.cibc.viewmodel.LandingViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            CIBCTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = LANDING_ROUTE) {
                        composable(LANDING_ROUTE) {
                            LandingScreen()
                        }
                        composable(DETAIL_ROUTE) {
                            DetailScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LandingScreen(
    viewModel: LandingViewModel = viewModel()
) {
    Scaffold { innerPadding ->
        val data = viewModel.uiState.collectAsState()
        when (data.value) {
            is Error -> {}
            is Loading -> {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    CircularProgressIndicator()
                }
            }

            is Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    (data.value as Success).accounts.forEach { account ->
                        AccountItem(account = account) { account ->

                        }
                    }
                }
            }
        }
    }
}


@Composable
fun AccountItem(
    account: Account,
    onClickItem: (Account) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onClickItem(account)
            }
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = account.name)
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = account.accountId)
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = account.amount.toString())
    }
}


@Composable
fun DetailScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(text = "WELCOME TO DETAIL SCREEN")
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CIBCTheme {
        Greeting("Android")
    }
}