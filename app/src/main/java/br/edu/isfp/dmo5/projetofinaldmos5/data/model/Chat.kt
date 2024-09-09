package br.edu.isfp.dmo5.projetofinaldmos5.data.model

class Chat(
    val loggedUser: User,
    val msg : String = "",
    val msgToUser: User
) {

}