package `in`.webdevlabs.campuscommerce.adapters

/**
 * Created by yolo on 7/4/18.
 */
import `in`.webdevlabs.campuscommerce.App
import `in`.webdevlabs.campuscommerce.R
import `in`.webdevlabs.campuscommerce.activities.ChatActivity
import `in`.webdevlabs.campuscommerce.model.Post
import `in`.webdevlabs.campuscommerce.model.PostType
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var name: TextView = itemView.findViewById(R.id.name) as TextView
    var price: TextView = itemView.findViewById(R.id.price) as TextView
    var type: TextView = itemView.findViewById(R.id.type) as TextView
    var time: TextView = itemView.findViewById(R.id.time) as TextView
    var chat: ImageView = itemView.findViewById(R.id.chat) as ImageView
    val tag = itemView.findViewById(R.id.tag) as TextView

    fun bindPost(post: Post) {
        this.name.text = post.name
        this.price.text = "RS " + post.price.toString()

        post.tag?.let {
            tag.text = "Category :" + it
        }

        this.type.text = when (post.type) {
            PostType.RENT -> PostType.RENT.toString()
            PostType.SELL -> "BUY"
            else -> ""
        }

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
        post.time?.run {
            time.text = DateUtils.getRelativeTimeSpanString(sdf.parse(post.time).time, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS)
        }

        if (post.uid == FirebaseAuth.getInstance().currentUser?.uid) {
            chat.visibility = View.GONE
        } else {
            this.chat.setOnClickListener {
                val intent = Intent(App.applicationContext(), ChatActivity::class.java)
                intent.putExtra("uid", post.uid)
                intent.putExtra("pid", post.pid)
                App.applicationContext().startActivity(intent)
            }
        }

    }
}