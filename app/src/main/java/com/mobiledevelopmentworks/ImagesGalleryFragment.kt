package com.mobiledevelopmentworks

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.DefaultLayoutHelper
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import java.util.*


class ImagesGalleryFragment : Fragment() {

    lateinit var imagesRecyclerViewAdapter: ImagesRecyclerViewAdapter
    private val imagesList = mutableListOf<String>()
    private val GET_IMAGE: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_images_gallery, container, false)

        val imageRecyclerView:RecyclerView =  root.findViewById(R.id.images_recyclerview)
        //val layoutManager = VirtualLayoutManager(requireContext())
        imageRecyclerView.layoutManager = GridLayoutManager(context,4)
        imagesRecyclerViewAdapter = ImagesRecyclerViewAdapter(context, imagesList)
        imageRecyclerView.adapter = imagesRecyclerViewAdapter

        val helpers= LinkedList<LayoutHelper>()
        val gridLayoutHelper = GridLayoutHelper(4)
        helpers.add(DefaultLayoutHelper.newHelper(2))
        helpers.add(gridLayoutHelper)

        //layoutManager.setLayoutHelpers(helpers)

        /*val viewPool = RecyclerView.RecycledViewPool()
        imageRecyclerView.setRecycledViewPool(viewPool)
        viewPool.setMaxRecycledViews(0,8)*/

        root.findViewById<Button>(R.id.load_image_button).setOnClickListener{
            val photoPickerIntent = Intent(Intent.ACTION_PICK);

            photoPickerIntent.type = "image/*";
            startActivityForResult(photoPickerIntent, GET_IMAGE);
        }



        return root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GET_IMAGE){
            if (resultCode == RESULT_OK){
                imagesList.add(data?.data.toString())
                imagesRecyclerViewAdapter.notifyDataSetChanged()
            }
        }
    }
}