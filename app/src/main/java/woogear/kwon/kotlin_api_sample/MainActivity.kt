package woogear.kwon.kotlin_api_sample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import woogear.kwon.kotlin_api_sample.adapters.MyAdapter
import woogear.kwon.kotlin_api_sample.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MyAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.initViewModel()
        initView()
        observeLiveData()
    }


    private fun observeLiveData(){
        viewModel.liveData.observe(this, Observer {
            layout_progressbar.visibility = View.GONE
            adapter.updateList(it!!)
            recycler_view.smoothScrollToPosition(0)
        })
    }

    private fun initAdapter(){
        adapter = MyAdapter(applicationContext)
        recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.adapter = adapter
    }

    private fun initView(){
        layout_progressbar.visibility = View.VISIBLE

        initAdapter()
        et_search.text.toString()
        btn_search.setOnClickListener { v ->
            if(!et_search.text.toString().equals("")){
                layout_progressbar.visibility = View.VISIBLE
                viewModel.searchImages(et_search.text.toString())
            }
        }
    }
}
