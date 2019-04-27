package woogear.kwon.kotlin_api_sample.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import woogear.kwon.kotlin_api_sample.data.SearchImage
import woogear.kwon.kotlin_api_sample.data.UnsplashImage
import woogear.kwon.kotlin_api_sample.utils.APIClient
import woogear.kwon.kotlin_api_sample.utils.APIInterface

/**
 * ViewModel for Unsplash Image API
 * This will fetch 20 photos by default from  -> https://api.unsplash.com/photos
 * Also can fetch searched image from  -> https://api.unsplash.com/search/photos?query=...
 * */

class MainViewModel : AndroidViewModel{

    private val imageList = ArrayList<UnsplashImage>()
    private val liveImages = MutableLiveData<List<UnsplashImage>>()
    val liveData: LiveData<List<UnsplashImage>> get() = liveImages

    constructor(application: Application) : super(application)

    fun initViewModel(){
        getImages()
    }

    private fun getImages(){
        val api: APIInterface = APIClient.getClient().create(APIInterface::class.java)
        val observable: Observable<List<UnsplashImage>> = api.getPhotos(1, 20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val cp = CompositeDisposable()
        cp.add(observable.subscribe { images ->
            imageList.addAll(images)
            liveImages.postValue(imageList)
        })
    }

    fun searchImages(query: String){
        val api: APIInterface = APIClient.getClient().create(APIInterface::class.java)
        val observable: Observable<SearchImage> = api.searchPhotos(query, 1, 20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val cp = CompositeDisposable()
        cp.add(observable.subscribe { images ->
            imageList.clear()
            imageList.addAll(images.results)
            liveImages.postValue(imageList)
        })
    }


}