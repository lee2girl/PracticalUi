package com.million.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.million.ui.activity.BaseActivity
import com.million.ui.activity.FloatDragViewActivity
import com.million.ui.adapter.MainAdapter
import com.million.ui.beans.MainBean
import com.million.ui.databinding.ActivityMainBinding
import com.million.ui.ui.theme.PracticalUiTheme
import java.util.Arrays

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    private val onItemListener = MainAdapter.OnItemClick{
        startActivity(Intent(this,getActivity(it)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        adapter = MainAdapter(genMainData(),onItemListener)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun genMainData(): ArrayList<MainBean> {
        val data = ArrayList<MainBean>()
        data.addAll(listOf(MainBean(0,"拖动悬浮小球")))
        return data
    }

    private fun getActivity(pos:Int):Class<Activity>{
        return when(pos){
            0 -> {
                FloatDragViewActivity::class.java as Class<Activity>
            }
            else -> {
                FloatDragViewActivity::class.java as Class<Activity>
            }
        }
    }


}

