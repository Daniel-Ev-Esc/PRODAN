package com.example.prodan.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pet(
    @SerializedName("data")
    val `data`: List<Data>
): Parcelable

@Parcelize
data class Data(
    @SerializedName("attributes")
    val attributes: Attributes,
    @SerializedName("id")
    val id: Int
): Parcelable

@Parcelize
data class Attributes(
    @SerializedName("age")
    val age: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: Image?,
    @SerializedName("male")
    val male: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    val url: String
): Parcelable

@Parcelize
data class Image(
    @SerializedName("data")
    val `data`: DataX
): Parcelable

@Parcelize
data class DataX(
    @SerializedName("attributes")
    val attributes: AttributesX,
    @SerializedName("id")
    val id: Int
): Parcelable

@Parcelize
data class AttributesX(
    @SerializedName("alternativeText")
    val alternativeText: String,
    @SerializedName("caption")
    val caption: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("ext")
    val ext: String,
    @SerializedName("formats")
    val formats: Formats,
    @SerializedName("hash")
    val hash: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("mime")
    val mime: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("provider_metadata")
    val providerMetadata: ProviderMetadataXXX,
    @SerializedName("size")
    val size: Double,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
): Parcelable

@Parcelize
data class Formats(
    @SerializedName("medium")
    val medium: Medium,
    @SerializedName("small")
    val small: Small,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
): Parcelable

@Parcelize
data class ProviderMetadataXXX(
    @SerializedName("public_id")
    val publicId: String,
    @SerializedName("resource_type")
    val resourceType: String
): Parcelable

@Parcelize
data class Medium(
    @SerializedName("ext")
    val ext: String,
    @SerializedName("hash")
    val hash: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("mime")
    val mime: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("path")
    val path: String,
    @SerializedName("provider_metadata")
    val providerMetadata: ProviderMetadataXXX,
    @SerializedName("size")
    val size: Double,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
): Parcelable

@Parcelize
data class Small(
    @SerializedName("ext")
    val ext: String,
    @SerializedName("hash")
    val hash: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("mime")
    val mime: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("path")
    val path: String,
    @SerializedName("provider_metadata")
    val providerMetadata: ProviderMetadataXXX,
    @SerializedName("size")
    val size: Double,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
): Parcelable

@Parcelize
data class Thumbnail(
    @SerializedName("ext")
    val ext: String,
    @SerializedName("hash")
    val hash: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("mime")
    val mime: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("path")
    val path: String,
    @SerializedName("provider_metadata")
    val providerMetadata: ProviderMetadataXXX,
    @SerializedName("size")
    val size: Double,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
): Parcelable

/*
@Parcelize
data class Data(
    @SerializedName("attributes")
    val attributes: Attributes,
    @SerializedName("id")
    val id: Int
) : Parcelable

@Parcelize
data class Attributes(
    @SerializedName("age")
    val age: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("male")
    val male: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updatedAt")
    val updatedAt: String
): Parcelable*/
