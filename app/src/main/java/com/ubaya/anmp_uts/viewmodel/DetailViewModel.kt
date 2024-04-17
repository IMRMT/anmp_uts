package com.ubaya.anmp_uts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubaya.anmp_uts.model.Berita

class DetailViewModel: ViewModel() {
    val beritaLD = MutableLiveData<Berita>()
    fun refresh(){
        val student1 = Berita(1,"Loch Martin","Loch Martin","Velociraptor is a genus of small dromaeosaurid dinosaurs that lived in Asia during the Late Cretaceous epoch, about 75 million to 71 million years ago. Two species are currently recognized, although others have been assigned in the past. The type species is V. mongoliensis, named and described in 1924.","dinosaur","12-02-2023","https://www.nhm.ac.uk/content/dam/nhmwww/discover/velociraptor-render-1200x675.jpg")
        beritaLD.value = student1
    }
}