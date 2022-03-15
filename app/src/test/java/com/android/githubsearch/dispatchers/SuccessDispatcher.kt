package com.android.githubsearch.dispatchers

import com.android.githubsearch.FileReader
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class SuccessDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.requestUrl?.queryParameter("page")) {
            "1" -> MockResponse().setResponseCode(200).setBody(FileReader.readStringFromFile("success_response.json"))
            "2" -> MockResponse().setResponseCode(200).setBody(FileReader.readStringFromFile("success_empty_response.json"))
            else -> MockResponse().setResponseCode(400)
        }
    }
}
