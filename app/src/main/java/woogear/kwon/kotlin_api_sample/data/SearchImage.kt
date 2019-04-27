package woogear.kwon.kotlin_api_sample.data


data class SearchImage (
    val total: Int,
    val total_pages: Int,
    val results: List<UnsplashImage>
)