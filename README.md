# TuroSampleApp
Sample app for Turo : Android developer role

This app interacts with the Yelp API, where user we display list of resutuarnts depending on choice of cusine in specified location provided,  
For example 
* Make a request to search for "pizza" near our San Fransisco.
* Make a second request to search for "beer" near office/home(Eg :111 Sutter Street, San Francisco, CA)

Yelp Endpoint : https://www.yelp.com/developers/documentation/v3/business_search

# Libraries used :

Pattern/Architecture : 
MVVM (Model-View-ViewModel) using Google's Architecture components and Jetpack libaries.

In addition to the above, using 
* Glide - downloads and caches images
* Retrofit2 - provides Java interface for REST API
* OkHttp MockWebServer - assists in testing Retrofit API
