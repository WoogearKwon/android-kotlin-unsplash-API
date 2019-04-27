package woogear.kwon.kotlin_api_sample.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashImage (

    val id: String,
    val created_at: String,
    val description: String,
    val urls: UnsplashUrls,
    val user: UnsplashUser

) : Parcelable