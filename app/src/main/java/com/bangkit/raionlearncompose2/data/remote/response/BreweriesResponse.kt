package com.bangkit.raionlearncompose2.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class BreweriesResponseItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("brewery_type")
	val breweryType: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("address_1")
	val address1: String? = null,

	@field:SerializedName("address_2")
	val address2: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("address_3")
	val address3: String? = null,

	@field:SerializedName("state_province")
	val stateProvince: String? = null,

	@field:SerializedName("website_url")
	val websiteUrl: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("street")
	val street: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("postal_code")
	val postalCode: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
) : Parcelable
