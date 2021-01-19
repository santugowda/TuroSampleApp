package com.example.turosampleapp.client

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.turosampleapp.BaseTest
import com.example.turosampleapp.MockResponseFileReader
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class YelpBusinessApiTest : BaseTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `read sample success json file`() {
        val reader = MockResponseFileReader("yelpSearchResponse.json")
        Assert.assertNotNull(reader)
    }

    @Test
    fun `get Search result and check response Code 200 returned`() = runBlocking{
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("yelpSearchResponse.json").content)
        mockWebServer.enqueue(response)

        val foodTypeLocation : MutableMap<String, String> = HashMap()
        foodTypeLocation["term"] = "beer"
        foodTypeLocation["location"] = "San Francisco"

        val actualResponse = service.getBusinessSearch(foodTypeLocation).execute()
        Assert.assertEquals(
            response.toString().contains("200"),
            actualResponse.code().toString().contains("200")
        )
    }

    @Test
    fun `get Search result and check response Code 400 returned`() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(MockResponseFileReader("yelpSearchResponse.json").content)
        mockWebServer.enqueue(response)
        val foodTypeLocation : MutableMap<String, String> = HashMap()
        foodTypeLocation["term"] = "beer"
        foodTypeLocation["location"] = "San Jose"

        val actualResponse = service.getBusinessSearch(foodTypeLocation).execute()
        Assert.assertEquals(
            response.toString().contains("400"),
            actualResponse.toString().contains("400")
        )
    }

    @Test
    fun `get Search Result and check response`() = runBlocking{
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("yelpSearchResponse.json").content)
        mockWebServer.enqueue(response)
        val mockResponse = response.getBody()?.readUtf8()

        val foodTypeLocation : MutableMap<String, String> = HashMap()
        foodTypeLocation["term"] = "beer"
        foodTypeLocation["location"] = "San Francisco"

        val actualResponse = service.getBusinessSearch(foodTypeLocation).execute()
        Assert.assertNotNull(actualResponse)
        Assert.assertEquals(actualResponse.body()?.businesses?.get(0)?.name, "City Beer Store")
        Assert.assertEquals(actualResponse.body()?.businesses?.get(0)?.price, "$$")
        Assert.assertEquals(actualResponse.body()?.businesses?.get(0)?.rating, 4.5)
        Assert.assertEquals(actualResponse.body()?.businesses?.get(0)?.reviewCount, 770)
        Assert.assertEquals(actualResponse.body()?.businesses?.get(0)?.phone, "+14155031033")
        Assert.assertEquals(actualResponse.body()?.businesses?.get(0)?.isClosed, false)
        Assert.assertEquals(actualResponse.body()?.businesses?.get(0)?.imageUrl, "https://s3-media2.fl.yelpcdn.com/bphoto/SlHnVec1V8mdyiXiBjU9Zg/o.jpg")
    }
}