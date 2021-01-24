package com.mehyo.nyttopstories
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NYTModel(val results:ArrayList<Results>):Parcelable
@Parcelize
data class Results(
    val title: String, // New Pandemic Plight: Hospitals Are Running Out of Vaccines
    @SerializedName("abstract")
    val abstract_article: String, // Health officials are frustrated that available doses are going unused while the virus is killing thousands of people each day. Many vaccine appointments have been canceled.
    val url: String, // https://www.nytimes.com/2021/01/23/us/coronavirus-vaccines-canceled-appointments-shortages.html
    val published_date: String, // 2021-01-23T03:00:09-05:00
    val multimedia: ArrayList<Multimedia>,
    val short_url: String // https://nyti.ms/3peXshO
):Parcelable
@Parcelize
data class Multimedia(
    val url: String // https://static01.nyt.com/images/2021/01/22/us/22VIRUS-VACCSHORTAGE-texas/merlin_182612670_72d9392d-e96c-428e-bdd3-fa67291e4363-superJumbo.jpg
):Parcelable