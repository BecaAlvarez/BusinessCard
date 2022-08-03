package com.beca.businesscard

import android.app.Application
import com.beca.businesscard.data.AppDataBase
import com.beca.businesscard.data.BusinessCardRepository

//Classe inicializadora da aplicação
class App: Application() {
    //inicializa o room e o repositorio
    val database by lazy { AppDataBase.getDatabase(this) }
    val repository by lazy { BusinessCardRepository(database.businessDao()) }

}