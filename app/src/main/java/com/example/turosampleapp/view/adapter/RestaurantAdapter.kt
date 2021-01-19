package com.example.turosampleapp.view.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.turosampleapp.R
import com.example.turosampleapp.model.Business
import kotlinx.android.synthetic.main.restaurant_list_item.view.*

class RestaurantAdapter(
    private val businessList: ArrayList<Business>,
    private val clickListener: OnBusinessEventListener
) : RecyclerView.Adapter<RestaurantAdapter.BusinessItemsViewHolder>() {

    interface OnBusinessEventListener {
        fun onBusinessSelected(business: Business)
    }

    fun addAll(businessListItems: List<Business>) {
        val size = businessList.size
        businessList.clear()
        businessList.addAll(businessListItems);
        notifyItemRangeRemoved(0, size);
        notifyItemRangeInserted(0, businessListItems.size);
    }

    fun reset() {
        businessList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessItemsViewHolder {
        return BusinessItemsViewHolder(
            (LayoutInflater.from(parent.context)
                .inflate(R.layout.restaurant_list_item, parent, false))
        )
    }

    override fun onBindViewHolder(holder: BusinessItemsViewHolder, position: Int) {
        holder.bind(businessList[position])
    }

    override fun getItemCount(): Int {
        return businessList.size
    }

    inner class BusinessItemsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(business: Business) {
            with(itemView) {
                restaurantName.text = business.name
                if(business.price.isNullOrEmpty()){
                    restaurantRatings.text =
                        context.getString(R.string.ratings_count).plus(business.rating)
                } else {
                    restaurantRatings.text =
                        context.getString(R.string.ratings_count).plus(business.rating).plus("-").plus(business.price)
                }

                restaurantPhoneNumber.text =
                    context.getString(R.string.phone_number).plus(business.displayPhone)

                if (business.isClosed) {
                    restaurantOpenClosed.text = context.getString(R.string.open)
                } else {
                    restaurantOpenClosed.text = context.getString(R.string.closed)
                }

                Glide.with(context)
                    .load(business.imageUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(restaurantListImage)

                setOnClickListener {
                    clickListener.onBusinessSelected(business)
//                    it.findNavController()
//                        .navigate(R.id.action_RestaurantListFragment_to_RestaurantDetailFragment)
                }
            }
        }
    }
}