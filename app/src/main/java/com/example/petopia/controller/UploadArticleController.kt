package com.example.petopia.controller
import com.example.petopia.model.pojo.Article
import com.example.petopia.model.pojo.ServerResponse
import com.example.petopia.model.repository.Repository
import com.example.petopia.view.UploadArticle
import retrofit2.Call
import retrofit2.Response

class UploadArticleController(uploadAricle : UploadArticle) {
    private val repository = Repository()
    val uploadArticle = uploadAricle

    fun onAddArticle(title : String, content : String, image : String){
        val article = Article(title, content, image)

        repository.addArticle(article, object : retrofit2.Callback<ServerResponse?> {
            override fun onResponse(
                call: Call<ServerResponse?>,
                response: Response<ServerResponse?>
            ) {
                if (response.isSuccessful) {
                    val serverResponse = response.body()
                    if (serverResponse != null && "Successful" == serverResponse.message) {
                        uploadArticle.onSuccess(serverResponse.message)
                    } else {
                        uploadArticle.onError("Error on Adding article" + serverResponse!!.message)
                    }
                } else {
                    uploadArticle.onError("HTTP Error: " + response.code())
                }
            }

            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {
                uploadArticle.onError("Exception: " + t.message)
            }
        })

    }


}