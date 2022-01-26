import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("description")
    val description: String,
    @SerializedName("image_urls")
    val imageUrls: List<String>
)