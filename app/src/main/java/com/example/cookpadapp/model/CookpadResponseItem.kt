import com.google.gson.annotations.SerializedName

data class CookpadResponseItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("ingredients")
    val ingredients: List<String>,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("steps")
    val steps: List<Step>,
    @SerializedName("story")
    val story: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("user")
    val user: User
)