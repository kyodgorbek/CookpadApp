import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String
)