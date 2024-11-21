import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("capital") var capital: String? = "Unknown capital",
    @SerializedName("code") var code: String? = "Unknown code",
    @SerializedName("currency") var currency: CurrencyResponse? = CurrencyResponse(),
    @SerializedName("flag") var flag: String? = null,
    @SerializedName("language") var language: LanguageResponse? = LanguageResponse(),
    @SerializedName("name") var name: String? = "Unknown country",
    @SerializedName("region") var region: String?= "Unknown region"
)

data class LanguageResponse(
    @SerializedName("code") var code: String? = null,
    @SerializedName("name") var name: String? = null
)

data class CurrencyResponse(
    @SerializedName("code") var code: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("symbol") var symbol: String? = null
)