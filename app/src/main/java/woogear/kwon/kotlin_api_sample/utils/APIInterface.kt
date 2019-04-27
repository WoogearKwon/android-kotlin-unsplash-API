package woogear.kwon.kotlin_api_sample.utils

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import woogear.kwon.kotlin_api_sample.data.SearchImage
import woogear.kwon.kotlin_api_sample.data.UnsplashImage

interface APIInterface {


    @Headers(APIClient.ACCESS_KEY)
    @GET("photos")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Observable<List<UnsplashImage>>


    @Headers(APIClient.ACCESS_KEY)
    @GET("search/photos")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Observable<SearchImage>
}