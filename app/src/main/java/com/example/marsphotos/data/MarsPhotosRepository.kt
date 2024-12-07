/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.data

import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApiService

/**
 * Repository that fetch mars photos list from marsApi.
 */
interface MarsPhotosRepository {
    /** Fetches list of MarsPhoto from marsApi */
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

/**
 * Network Implementation of Repository that fetch mars photos list from marsApi.
 */
class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    /** Fetches list of MarsPhoto from marsApi*/
    // override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()

    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        val response = marsApiService.getPhotos(
            apiKey = "6be1167b2aa23c937099c4262e8956df",
            text = "Mars"
            // apiSecret = "6808354ceb99bdd2"
        )
        return response.photos.photo.map { flickrPhoto ->
            MarsPhoto(
                id = flickrPhoto.id,
                imgSrc = "https://farm${flickrPhoto.farm}.staticflickr.com/${flickrPhoto.server}/${flickrPhoto.id}_${flickrPhoto.secret}.jpg"
            )
        }
    }
}
