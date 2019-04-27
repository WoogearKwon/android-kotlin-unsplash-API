package woogear.kwon.kotlin_api_sample.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_view.view.*
import woogear.kwon.kotlin_api_sample.R
import woogear.kwon.kotlin_api_sample.data.UnsplashImage

/**
 * Image's recycler view adapter
 * 20 Images will be shown each time.
 * */

class MyAdapter(private val context: Context) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    private val list: ArrayList<UnsplashImage> = ArrayList<UnsplashImage>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageUrl = list.get(position).urls.thumb
        Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
            .into(holder.photo)

        holder.user.text = list.get(position).user.name
        holder.likes.text = list.get(position).user.total_likes.toString()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val photo: ImageView = v.iv_photo
        val user: TextView = v.tv_user
        val likes: TextView = v.likes
    }

    fun updateList(data: List<UnsplashImage>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}