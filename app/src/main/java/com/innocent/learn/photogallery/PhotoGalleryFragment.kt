package com.innocent.learn.photogallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PhotoGalleryFragment : Fragment() {
    private lateinit var photoRecyclerView: RecyclerView
    private lateinit var photoGalleryViewModel: PhotoGalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoGalleryViewModel = ViewModelProvider(this).get(PhotoGalleryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photo_gallery, container, false)
        photoRecyclerView = view.findViewById(R.id.photo_recycler_view)
        photoRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        photoGalleryViewModel.galleryItemLiveData.observe(
            viewLifecycleOwner, { galleryItem ->
                photoRecyclerView.adapter = this.PhotoAdapter(galleryItem)
        })
        return view
    }

    inner class PhotoHolder(itemTextView: TextView) : RecyclerView.ViewHolder(itemTextView) {
        val bindTitle: (CharSequence) -> Unit = itemTextView::setText
    }

    inner class PhotoAdapter(private val galleryItems: List<GalleryItem>)
        : RecyclerView.Adapter<PhotoHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
            val textView = TextView(parent.context)
            return PhotoHolder(textView)
        }

        override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
            val galleryItem = galleryItems[position]
            holder.bindTitle(galleryItem.title)
        }

        override fun getItemCount() = galleryItems.size
    }

    companion object {
        fun newInstance(): PhotoGalleryFragment {
            val args = Bundle()
            val fragment = PhotoGalleryFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
